package io.philipg.twitter.producer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamTweetEventServiceTest {
    @Autowired
    private TwitterTemplate twitterCreator;

    @Test
    public void whenTweeting_thenNoExceptions() {
        SearchResults searchResults = twitterCreator.searchOperations().search("Spring Boot");
        assertThat(searchResults).isNotNull();
    }
}
