package com.practice1.TicketManagement.service;

import com.practice1.TicketManagement.dao.TicketDao;
import com.practice1.TicketManagement.model.Ticket;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TicketServiceImplementation implements TicketServiceInterface {
    @Autowired
    private TicketDao ticketDao;
    @Override
    public Ticket saveTicket(Ticket ticket) {return ticketDao.save(ticket);}


    @Override
    public List<Ticket> findAllTickets() {return ticketDao.findAll();}



    @Override
    public Ticket findTicketById(Integer id) {
        Ticket ticket = ticketDao.findById(id).orElse(null);
        if(ticket == null){
            throw new ObjectNotFoundException
                    (Ticket.class, String.valueOf(id));
        } else {
            return ticket;
        }

    }

    @Override
    public Ticket updateDestinationById(Integer id, String destination) throws Exception {
        Ticket ticket = ticketDao.findById(id).orElse(null);
        if(ticket != null){
            ticket.setDestination(destination);
            return ticketDao.save(ticket);
        } else {
            throw new ObjectNotFoundException
                    (Ticket.class, String.valueOf(id));
        }

    }

    @Override
    public boolean deleteTicketById(Integer id) {
        Ticket ticket = ticketDao.findById(id).orElse(null);
        if(ticket != null){
            ticketDao.delete(ticket);
            return true;
        }
        return false;
    }
}
