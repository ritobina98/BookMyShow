package dev.ritobina.BookMyShow.services;

import dev.ritobina.BookMyShow.dtos.TicketReqDTO;
import dev.ritobina.BookMyShow.exceptions.SelectedSeatsNotAvailableException;
import dev.ritobina.BookMyShow.exceptions.TicketNotFoundException;
import dev.ritobina.BookMyShow.models.ShowSeat;
import dev.ritobina.BookMyShow.models.Ticket;
import dev.ritobina.BookMyShow.models.User;
import dev.ritobina.BookMyShow.models.constant.ShowSeatStatus;
import dev.ritobina.BookMyShow.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ShowService showService;
    @Autowired
    private ShowSeatService showSeatService;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    //multiple threads can enter the method because we can create multiple tickets at the same time.
    // We are isolating at the DB level. Only one thread will get access to DB level
    public Ticket createTicket(TicketReqDTO ticketDTO){
        User user = userService.getUserById(ticketDTO.getUserId());
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Integer i : ticketDTO.getShowSeatIds()){
            showSeats.add(showSeatService.getShowSeatById(i));
        }
        //CHECK IF SEATS ARE AVAILABLE
        for(ShowSeat showSeat : showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE))){
                throw new SelectedSeatsNotAvailableException("Selected seats are not available");
            }
        }
        //LOCK THE SEATS
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeatService.updateShowSeat(showSeat);
        }

        //PAYMENT TAKES TIME
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShowSeats(showSeats);
        ticket.setShow(showSeats.getFirst().getShow());
        ticket = ticketRepository.save(ticket);

        //BOOK THE SEATS
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatService.updateShowSeat(showSeat);
        }


        return generateTicket(user,showSeats);
    }

    private Ticket generateTicket(User user, List<ShowSeat> showSeats){
        //PAYMENT DONE HERE
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setShowSeats(showSeats);
        ticket.setShow(showSeats.getFirst().getShow());
        ticket = ticketRepository.save(ticket);

        //BOOK THE SEATS
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatService.updateShowSeat(showSeat);
        }
        return ticket;
    }
    public Ticket getTicketById(int id){
        return ticketRepository.findById(id).orElseThrow(
                () -> new TicketNotFoundException("Ticket with id "+id+" not found")
        );
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public void deleteTicketById(int id){
        ticketRepository.deleteById(id);
    }
}

/*
    Show -> open layout [ _ _ _ X X ] -> BOOK
    START TRANSACTION;
    Step 1. Checking the availability of the seats
    Step 2. Lock them from being available to other customers
    Step 3. Payment completes, ticket is generated, mark the showSeats as BOOKED
            If payment fails
            mark the showSeats as AVAILABLE
    END TRANSACTION;


    a. Checking in layout
    b. Taking a lock -> SERIALIZABLE TRANSACTION
    c. Checking again


    1
    2
    3
    4
    5

    Book Seat -> 1 2
    [1 2] -> lock -> SERIALIZABLE
    payment completes || 10 mins -> lock would be very resource intensive ?
    unlock

    TRANSACTION 1 -> moves the seat from AVAILABLE TO LOCKED || SERIALIZABLE
    thread lock on rows 1,2 until we change the status of the seats from AVAILABLE -> LOCKED

        ------- resources are free in the period until payment is being done ------
        ------- complete the payment ------

    TRANSACTION 2 -> moves the seat from LOCKED TO BOOKED || DEFAULT -> let JPA decide
 */