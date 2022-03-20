package com.ticketmaster.repository;

import com.ticketmaster.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
}
