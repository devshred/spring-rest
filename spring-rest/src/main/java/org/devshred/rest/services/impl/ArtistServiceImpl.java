package org.devshred.rest.services.impl;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import org.devshred.rest.domain.Artist;
import org.devshred.rest.services.ArtistService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistService {
    private static final Map<String, Artist> ARTIST_DB = new HashMap<>();

    static {
        ARTIST_DB.put("Paul", new Artist("Paul"));
        ARTIST_DB.put("George", new Artist("George"));
        ARTIST_DB.put("Ringo", new Artist("Ringo"));
        ARTIST_DB.put("John", new Artist("John"));
    }

    @Override
    public Optional<Artist> findByName(final String name) {
        if (ARTIST_DB.containsKey(name)) {
            return Optional.of(ARTIST_DB.get(name));
        } else {
            return Optional.absent();
        }
    }

    @Override
    public Set<Artist> findAll() {
        return ImmutableSet.copyOf(ARTIST_DB.values());
    }

    @Override
    public Artist create(final String name) {
        Artist artist = new Artist(name);
        ARTIST_DB.put(name, artist);
        return artist;
    }

    @Override
    public void delete(String name) {
        if (ARTIST_DB.containsKey(name)) {
            ARTIST_DB.remove(name);
        }
    }
}