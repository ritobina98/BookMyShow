package dev.ritobina.BookMyShow.services;

import dev.ritobina.BookMyShow.exceptions.ShowNotFoundException;
import dev.ritobina.BookMyShow.models.Seat;
import dev.ritobina.BookMyShow.models.Show;
import dev.ritobina.BookMyShow.models.ShowSeat;
import dev.ritobina.BookMyShow.models.constant.ShowSeatStatus;
import dev.ritobina.BookMyShow.repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatService showSeatService;

    public Show createShow(Show show){
        //create showseats for the show
        List<ShowSeat> showSeats = new ArrayList<>();
        show = showRepository.save(show);

        List<Seat> seats = show.getAuditorium().getSeats();
        for(Seat seat: seats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setPrice(100);
            showSeat.setShow(show);
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeat = showSeatService.createShowSeat(showSeat);
            showSeats.add(showSeat);
        }
        show.setShowSeats(showSeats);
        //return showRepository.save(show);
        return show;
    }

    public Show getShowById(int id){
        return showRepository.findById(id).orElseThrow(
                () -> new ShowNotFoundException("Show with id "+id+" not found")
        );
    }
    public List<Show> getAllShows(){
        return showRepository.findAll();
    }

    public void deleteShowById(int id){
        showRepository.deleteById(id);
    }
}
