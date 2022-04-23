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
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(EventNotFoundException::new);
    }

    @Override
    public Event create(LocalDate date, String name, Float duration, Float price, String url, String description,List<Long> artistsIds,Long venueId) {

        List<Artist> artists = this.artistRepository.findAllById(artistsIds);
        Venue venue = this.venueRepository.findById(venueId).orElseThrow(VenueNotFoundException::new);
        Event event = new Event(date,name,duration,price,url,description,artists,venue);

        return this.eventRepository.save(event);
    }

    @Override
    public Event update(Long id, LocalDate date, String name, Float duration, Float price, String url, String description, List<Long> artistsIds,Long venueId) {
        Event event = this.findById(id);
        List<Artist> artists = this.artistRepository.findAllById(artistsIds);
        Venue venue = this.venueRepository.findById(venueId).orElseThrow(VenueNotFoundException::new);
        event.setDate(date);
        event.setName(name);
        event.setDuration(duration);
        event.setPrice(price);
        event.setUrl(url);
        event.setDescription(description);
        event.setArtists(artists);
        event.setVenue(venue);

        return this.eventRepository.save(event);
    }

    @Override
    public Event  delete(Long id) {
        Event event =  findById(id);
        this.eventRepository.delete(event);
        return event;
    }

}

