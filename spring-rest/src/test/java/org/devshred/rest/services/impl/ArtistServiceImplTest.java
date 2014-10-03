package org.devshred.rest.services.impl;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import org.devshred.rest.domain.Artist;
import org.devshred.rest.persistence.ArtistStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ArtistServiceImplTest {
    private static final long ARTIST_ID = 1L;
    private static final String ARTIST_NAME = "Paul";
    private static final Artist ARTIST = new Artist(ARTIST_ID, ARTIST_NAME);

    @Mock
    ArtistStore store;

    @InjectMocks
    private ArtistServiceImpl service;

    @Test
    public void findByName_nothingFound() {
        when(store.findByName(ARTIST_NAME)).thenReturn(Collections.<Artist>emptyList());
        assertThat(service.findByName(ARTIST_NAME).isPresent(), is(false));
    }

    @Test
    public void findByName_somethingFound() {
        when(store.findByName(ARTIST_NAME)).thenReturn(ImmutableList.of(ARTIST));
        final Optional<Artist> artistOptional = service.findByName(ARTIST_NAME);
        assertThat(artistOptional.isPresent(), is(true));
        assertThat(artistOptional.get().getName(), is(ARTIST_NAME));
    }

    @Test
    public void findAllDelegatesToStore() {
        service.findAll();
        verify(store).findAll();
    }

    @Test
    public void createNewArtist() {
        when(store.save(any(Artist.class))).thenReturn(ARTIST);
        assertThat(service.create(ARTIST_NAME).getId(), is(ARTIST_ID));
    }

    @Test
    public void deleteArtist() {
        when(store.findByName(ARTIST_NAME)).thenReturn(ImmutableList.of(ARTIST));
        service.delete(ARTIST_NAME);
        verify(store).remove(ARTIST);
    }
}
