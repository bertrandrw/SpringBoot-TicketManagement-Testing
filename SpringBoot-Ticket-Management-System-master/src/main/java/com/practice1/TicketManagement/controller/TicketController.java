package com.practice1.TicketManagement.controller;

import com.practice1.TicketManagement.model.Ticket;
import com.practice1.TicketManagement.service.TicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/id")
public class TicketController {

    @Autowired
    private TicketServiceInterface ticketServiceInterface;

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket){
        Ticket savedTicket =   ticketServiceInterface.saveTicket(ticket);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAll(){
        List<Ticket> tickets = ticketServiceInterface.findAllTickets();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Object> getTicketById(@PathVariable("id") Integer id) {
        try {
            Ticket ticket = ticketServiceInterface.findTicketById(id);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Integer id, @RequestBody Ticket ticket) {
        Ticket existingTicket = ticketServiceInterface.findTicketById(id);
        if(existingTicket != null){
            Ticket updatedTicket = new Ticket();
            updatedTicket.setTicketId(id);
            updatedTicket.setPassengerName(ticket.getPassengerName());
            updatedTicket.setSource(ticket.getSource());
            updatedTicket.setDestination(ticket.getDestination());
            return new ResponseEntity<>(ticketServiceInterface.saveTicket(updatedTicket), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") Integer id) {
        try {
            ticketServiceInterface.deleteTicketById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
