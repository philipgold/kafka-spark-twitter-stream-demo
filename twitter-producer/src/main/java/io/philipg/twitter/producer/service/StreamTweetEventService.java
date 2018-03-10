package io.philipg.twitter.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StreamTweetEventService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#\\w+");

    private final String kafkaTopic;
    private final Twitter twitter;
    private final KafkaProducer kafkaProducer;

    public StreamTweetEventService (Twitter twitter,
                                    KafkaProducer kafkaProducer,
                                    @Value(value = "${spring.kafka.template.default-topic}") String kafkaTopic){
        this.twitter = twitter;
        this.kafkaProducer = kafkaProducer;
        this.kafkaTopic = kafkaTopic;
    }

    public void run() {
        List<StreamListener> listeners = new ArrayList<StreamListener>();

        StreamListener streamListener = new StreamListener() {

            @Override
            public void onTweet(Tweet tweet) {
                String lang = tweet.getLanguageCode();
                String text = tweet.getText();

                //filter non-English tweets:
                if (!"en".equals(lang)) {
                    return;
                }

                Set<String> hashTags = hashTagsFromTweet(text);

                // filter tweets without hashTags:
                if (hashTags.isEmpty()) {
                    return;
                }
                //Send tweet to Kafka topic
                log.info("User '{}', Tweeted : {}, from ; {}", tweet.getUser().getName() , tweet.getText(), tweet.getUser().getLocation());
                kafkaProducer.send(kafkaTopic, tweet.getText());
            }

            @Override
            public void onWarning(StreamWarningEvent warningEvent) {
            }

            @Override
            public void onLimit(int numberOfLimitedTweets) {
            }

            @Override
            public void onDelete(StreamDeleteEvent deleteEvent) {
            }
        };

        //Start Stream when run a service
        listeners.add(streamListener);
        twitter.streamingOperations().sample(listeners);
    }

    private static Set<String> hashTagsFromTweet(String text) {
        Set<String> hashTags = new HashSet<>();
        Matcher matcher = HASHTAG_PATTERN.matcher(text);
        while (matcher.find()) {
            String handle = matcher.group();
            // removing '#' prefix
            hashTags.add(handle.substring(1));
        }
        return hashTags;
    }
}
