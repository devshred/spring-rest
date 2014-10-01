package org.devshred.rest.config;

import org.devshred.rest.config.WebAppInitializer;
import org.devshred.rest.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppInitializer.class, WebConfig.class}, loader = AnnotationConfigContextLoader.class)
public class SpringTest {
    @Test
    public void startingSpringWithoutAnyExceptions() {
    }
}