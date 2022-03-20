package com.ticketmaster.service;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Event;
import com.ticketmaster.model.Venue;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<Event> listAll();

    Event findById(Long id);

    Event create(LocalDate date, String name, Float duration, Float price, List<Artist> artists, Venue venue);

    Event update(Long id,LocalDate date, String name, Float duration, Float price, List<Artist> artists, Venue venue);

    Event delete(Long id);
}
