package org.devshred.rest.services;

import com.google.common.base.Optional;
import org.devshred.rest.domain.Artist;

import java.util.Set;

public interface ArtistService {
    Optional<Artist> findByName(String name);
    Set<Artist> findAll();
    Artist create(String name);
}