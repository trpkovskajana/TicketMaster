package com.ticketmaster.service.impl;

import com.ticketmaster.exceptions.ArtistNotFoundException;
import com.ticketmaster.model.Artist;
import com.ticketmaster.repository.ArtistRepository;
import com.ticketmaster.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listAll() {
        return this.artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return this.artistRepository.findById(id).orElseThrow(ArtistNotFoundException::new);
    }
}
