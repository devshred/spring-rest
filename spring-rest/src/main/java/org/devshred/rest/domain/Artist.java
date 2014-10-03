package org.devshred.rest.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artist")
public class Artist {
    private Long id;
    private String name;

    public Artist() {
    }

    public Artist(final String name) {
        this.name = name;
    }

    public Artist(final Long id, final String name) {
        this.id = id;
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