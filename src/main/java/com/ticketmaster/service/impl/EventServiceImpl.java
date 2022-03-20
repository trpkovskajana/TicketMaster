package com.ticketmaster.service.impl;

import com.ticketmaster.exceptions.EventNotFoundException;
import com.ticketmaster.exceptions.VenueNotFoundException;
import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Event;
import com.ticketmaster.model.Venue;
import com.ticketmaster.repository.ArtistRepository;
import com.ticketmaster.repository.EventRepository;
import com.ticketmaster.repository.VenueRepository;
import com.ticketmaster.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ArtistRepository artistRepository;
    private final VenueRepository venueRepository;

    public EventServiceImpl(EventRepository eventRepository, ArtistRepository artistRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.artistRepository = artistRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Event> listAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
    }

    @Override
    public Event create(LocalDate date, String name, Float duration, Float price, List<Artist> artists, Venue venue) {

        List<Artist> artistList = this.artistRepository.findAll();
        Venue venue1 = this.venueRepository.findById(venue.getId()).orElseThrow(VenueNotFoundException::new);
        Event event = new Event(date,name,duration,price,artistList,venue1);

        return this.eventRepository.save(event);
    }

    @Override
    public Event update(Long id, LocalDate date, String name, Float duration, Float price, List<Artist> artists, Venue venue) {
        Event event = this.findById(id);
        List<Artist> artistList = this.artistRepository.findAll();
        Venue venue1 = this.venueRepository.findById(venue.getId()).orElseThrow(VenueNotFoundException::new);
        event.setDate(date);
        event.setName(name);
        event.setDuration(duration);
        event.setPrice(price);
        event.setArtists(artistList);
        event.setVenue(venue1);

        return this.eventRepository.save(event);
    }

    @Override
    public Event delete(Long id) {
        Event event = this.findById(id);
        this.eventRepository.delete(event);
        return event;
    }
}
