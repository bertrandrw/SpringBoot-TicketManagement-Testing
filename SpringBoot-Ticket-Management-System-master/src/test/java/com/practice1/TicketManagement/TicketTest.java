package com.practice1.TicketManagement;

import com.practice1.TicketManagement.model.Ticket;
import com.practice1.TicketManagement.service.TicketServiceInterface;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TicketTest {

    @Autowired
    private TicketServiceInterface ticketServiceInterface;

    @Test
    public void testSave(){
        Ticket t = new Ticket();
        t.setTicketId(5);
        t.setPassengerName("lea");
        t.setSource("Burundi");
        t.setDestination("uganda");

        Ticket ticket = ticketServiceInterface.saveTicket(t);
        assertNotNull(ticket);
    }

    @Test
    public void testFindAll(){
        List<Ticket> tickets = ticketServiceInterface.findAllTickets();
        assertEquals(5, tickets.size());
    }

    @Test
    public void testFindById(){
        Ticket ticket = ticketServiceInterface.findTicketById(3);
        assertEquals("Madagascar",ticket.getDestination());
    }

    @Test
    public void testUpdateDestination() throws Exception{
        Ticket updatedTicket = ticketServiceInterface.updateDestinationById(2, "south africa");
        assertEquals("south africa",updatedTicket.getDestination());

    }

    @Test
    public void testUpdateDestinationNegative() throws Exception{
        assertThrows(ObjectNotFoundException.class,
                () -> {ticketServiceInterface
                        .updateDestinationById
                                (7, "egypt");
                });
    }

    @Test
    public void testDelete() {
        boolean isDeleted = ticketServiceInterface.deleteTicketById(5);
        assertTrue(isDeleted);
    }

}
