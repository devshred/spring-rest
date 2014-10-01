package org.devshred.rest.controller;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import org.devshred.rest.domain.Artist;
import org.devshred.rest.services.ArtistService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ArtistControllerTest {
    private static final String ARTIST_NAME = "Paul";
    private static final Artist ARTIST = new Artist(ARTIST_NAME);

    private MockMvc mockMvc;

    @Mock
    ArtistService artistService;

    @InjectMocks
    private
    ArtistController artistController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(artistController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void statusNotFound_IfArtistDontExists() throws Exception {
        when(artistService.findByName(ARTIST_NAME)).thenReturn(Optional.<Artist>absent());

        this.mockMvc.perform(
                get("/artist/{name}", ARTIST_NAME).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void returnsArtistAndStatusOK_IfArtistDontExists() throws Exception {
        when(artistService.findByName(ARTIST_NAME)).thenReturn(Optional.of(ARTIST));

        this.mockMvc.perform(
                get("/artist/{name}", ARTIST_NAME).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(ARTIST_NAME)));
    }

    @Test
    public void returnsAllArtistsAndStatusOK() throws Exception {
        when(artistService.findAll()).thenReturn(ImmutableSet.of(ARTIST));

        this.mockMvc.perform(
                get("/artist/").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name", is(ARTIST_NAME)));
    }
}