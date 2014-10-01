package org.devshred.rest.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.concurrent.atomic.AtomicLong;

@XmlRootElement(name = "artist")
public class Artist {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1L);

    private Long id;
    private String name;

    public Artist() {
    }

    public Artist(final String name) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
    }

    @XmlElement
    public Long getId() {
        return id;
    }

    @XmlElement
    public String getName() {
        return name;
    }
}