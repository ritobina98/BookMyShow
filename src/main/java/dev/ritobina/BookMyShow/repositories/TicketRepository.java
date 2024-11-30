package dev.ritobina.BookMyShow.repositories;

import dev.ritobina.BookMyShow.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
