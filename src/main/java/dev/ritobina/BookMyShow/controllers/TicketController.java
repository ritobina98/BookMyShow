package dev.ritobina.BookMyShow.controllers;

import dev.ritobina.BookMyShow.dtos.TicketReqDTO;
import dev.ritobina.BookMyShow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity createTicket(@RequestBody TicketReqDTO ticketReqDTO){
        return ResponseEntity.ok(ticketService.createTicket(ticketReqDTO));
    }
}
