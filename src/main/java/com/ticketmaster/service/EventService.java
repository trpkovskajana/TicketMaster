package com.ticketmaster.service;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Event;
import com.ticketmaster.model.Venue;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<Event> findAll();

    Event findById(Long id);

    Event create(LocalDate date, String name, Float duration, Float price, String url, String description, List<Long> artistsIds,Long venueId);

    Event update(Long id,LocalDate date, String name, Float duration, Float price, String url, String description, List<Long> artistsIds,Long venueId);

    Event delete(Long id);
}

