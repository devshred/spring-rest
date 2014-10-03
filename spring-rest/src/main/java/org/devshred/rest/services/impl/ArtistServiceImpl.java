package org.devshred.rest.services.impl;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import org.devshred.rest.domain.Artist;
import org.devshred.rest.persistence.ArtistStore;
import org.devshred.rest.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    ArtistStore store;

    @Override
    public Optional<Artist> findByName(final String name) {
        final Collection<Artist> artists = store.findByName(name);
        if (artists.isEmpty()) {
            return Optional.absent();
        } else {
            return Optional.of(artists.iterator().next());
        }
    }

    @Override
    public Set<Artist> findAll() {
        return ImmutableSet.copyOf(store.findAll());
    }

    @Override
    public Artist create(final String name) {
        final Artist artist = new Artist(name);
        return store.save(artist);
    }

    @Override
    public void delete(String name) {
        final Optional<Artist> artistOptional = findByName(name);
        if (artistOptional.isPresent()) {
            store.remove(artistOptional.get());
        }
    }
}