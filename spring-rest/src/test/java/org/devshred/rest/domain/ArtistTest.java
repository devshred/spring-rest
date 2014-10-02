package org.devshred.rest.domain;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ArtistTest {
    @Test
    public void setPrivateFieldViaReflection() {
        Artist artist = new Artist();
        assertThat(artist.getId(), nullValue());
        ReflectionTestUtils.setField(artist, "id", 1L);
        assertThat(artist.getId(), is(1L));
    }
}
