package dev.ritobina.BookMyShow.services;

import dev.ritobina.BookMyShow.models.*;
import dev.ritobina.BookMyShow.models.constant.SeatStatus;
import dev.ritobina.BookMyShow.models.constant.SeatType;
import dev.ritobina.BookMyShow.models.constant.ShowStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {

    @Autowired
    private CityService cityService;
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private SeatService seatService;
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowService showService;

    public void initialise(){

        //create seats
        List<Seat> seats = new ArrayList<>();
        for(int i=1;i<=100;i++){
            Seat seat = new Seat();
            seat.setSeatNumber("Seat - "+i);
            seat.setRow_c(i);
            seat.setCol_c(i);
            seat.setSeatStatus(SeatStatus.AVAILABLE);
            seat.setSeatType(SeatType.GOLD);
            seat = seatService.createSeat(seat);
            seats.add(seat);
        }

        //create auditorium
        Auditorium auditorium = new Auditorium();
        auditorium.setName("AUDI 01");
        auditorium.setCapacity(100);
        auditorium.setSeats(seats);
        auditorium = auditoriumService.createAuditorium(auditorium);

        //create theatre
        Theatre theatre = new Theatre();
        theatre.setName("PVR INOX CINEPOLIS");
        theatre.setAddress("Road1 1234");
        theatre.setAuditoriums(List.of(auditorium));
        theatre = theatreService.createTheatre(theatre);

        //create city
        City city = new City();
        city.setName("Kolkata");
        city.setTheatres(List.of(theatre));
        city = cityService.createCity(city);

        //create Movie
        Movie movie = new Movie();
        movie.setName("Christmas Santa");
        movie.setLanguage("English");
        movie = movieService.createMovie(movie);

        //create show
        Show show = new Show();
        show.setMovie(movie);
        show.setAuditorium(auditorium);
        show.setLanguage("English");
        show.setShowStatus(ShowStatus.YET_TO_START);
        show.setStartTime(LocalDateTime.now());
        show.setEndTime(LocalDateTime.now());
        show = showService.createShow(show);

    }
}
