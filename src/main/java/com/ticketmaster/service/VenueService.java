package com.ticketmaster.service;

import com.ticketmaster.model.Venue;
import com.ticketmaster.repository.VenueRepository;

import java.util.List;

public interface VenueService {

    List<Venue> listAll();

    Venue findById(Long id);
}
