package com.practice1.TicketManagement.service;

import com.practice1.TicketManagement.model.Ticket;

import java.util.List;

public interface TicketServiceInterface {

    Ticket saveTicket(Ticket ticket);
    List<Ticket> findAllTickets();
    Ticket findTicketById(Integer id);
    Ticket updateDestinationById
            (Integer id, String destination)
            throws Exception;
    boolean deleteTicketById(Integer id);
}
