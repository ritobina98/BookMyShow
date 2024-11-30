package dev.ritobina.BookMyShow.services;

import dev.ritobina.BookMyShow.exceptions.TheatreNotFoundException;
import dev.ritobina.BookMyShow.models.Theatre;
import dev.ritobina.BookMyShow.repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public Theatre createTheatre(Theatre theatre){
        return theatreRepository.save(theatre);
    }

    public Theatre getTheatreById(int id){
        return theatreRepository.findById(id).orElseThrow(
                () -> new TheatreNotFoundException("Theatre not found by id "+id)
        );
    }

    public List<Theatre> getAllTheatres(){
        return theatreRepository.findAll();
    }

    public void deleteTheatreById(int id){
        theatreRepository.deleteById(id);
    }
}
