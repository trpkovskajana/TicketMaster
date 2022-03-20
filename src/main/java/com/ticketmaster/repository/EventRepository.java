package com.ticketmaster.repository;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
