package dev.ritobina.BookMyShow.services;

import dev.ritobina.BookMyShow.exceptions.MovieNotFoundException;
import dev.ritobina.BookMyShow.models.Movie;
import dev.ritobina.BookMyShow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie getMovieById(int id){
        return movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException("Movie with id "+id+" not found")
        );
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public void deleteMovieById(int id){
        movieRepository.deleteById(id);
    }
}
