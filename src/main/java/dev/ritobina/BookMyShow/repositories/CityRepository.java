package dev.ritobina.BookMyShow.repositories;

import dev.ritobina.BookMyShow.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
