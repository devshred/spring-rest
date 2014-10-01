package org.devshred.rest.controller;

import com.google.common.base.Optional;
import org.devshred.rest.domain.Artist;
import org.devshred.rest.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    ArtistService service;

    @RequestMapping("/")
    public Set<Artist> getAll() {
        return service.findAll();
    }

    @RequestMapping("/{name}")
    public ResponseEntity<Artist> greeting(@PathVariable("name") String name) {
        Optional<Artist> artistOptional = service.findByName(name);
        if (artistOptional.isPresent()) {
            return new ResponseEntity<>(artistOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ResponseEntity<Artist> create(@PathVariable("name") String name, UriComponentsBuilder uriBuilder) {
        final Artist artist = service.create(name);
        final UriComponents uriComponents = uriBuilder.path("/artist/{name}").buildAndExpand(name);
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(artist, headers, HttpStatus.CREATED);
    }
}