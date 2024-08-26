package com.practice1.TicketManagement.dao;

import com.practice1.TicketManagement.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDao extends JpaRepository<Ticket, Integer> {
}
