package com.ticketmaster.service.impl;

import com.ticketmaster.exceptions.VenueNotFoundException;
import com.ticketmaster.model.Venue;
import com.ticketmaster.repository.VenueRepository;
import com.ticketmaster.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> listAll() {
        return this.venueRepository.findAll();
    }

    @Override
    public Venue findById(Long id) {
        return this.venueRepository.findById(id).orElseThrow(VenueNotFoundException::new);
    }
}
