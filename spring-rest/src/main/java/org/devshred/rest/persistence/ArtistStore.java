package org.devshred.rest.persistence;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.devshred.rest.domain.Artist;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ArtistStore {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1L);
    private static final Map<Long, Artist> ARTIST_DB = new HashMap<>();

    public Artist save(final Artist artist) {
        final Long key = artist.getId() != null ? artist.getId() : ID_GENERATOR.getAndIncrement();
        ARTIST_DB.put(key, new Artist(key, artist.getName()));
        return new Artist(key, artist.getName());
    }

    public Collection<Artist> findByName(final String name) {
        return Collections2.filter(ARTIST_DB.values(), new Predicate<Artist>() {
            @Override
            public boolean apply(final Artist artist) {
                return name.equals(artist.getName());
            }
        });
    }

    public Collection<Artist> findAll() {
        return ARTIST_DB.values();
    }

    public void remove(final Artist artist) {
        if (artist.getId() != null) {
            ARTIST_DB.remove(artist.getId());
        }
    }

    public void removeAll() {
        ARTIST_DB.clear();
    }
}
