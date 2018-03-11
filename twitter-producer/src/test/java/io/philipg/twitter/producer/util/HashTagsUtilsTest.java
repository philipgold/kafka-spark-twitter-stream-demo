package io.philipg.twitter.producer.util;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HashTagsUtilsTest {
    @Test
    public void hashTagsFromTweetTest(){
        final String testTweet = "#test1 #test2 Six of the Best Yellow Home Accessories";

        Iterator<String> actualHashTags = HashTagsUtils.hashTagsFromTweet(testTweet);
        List<String> expectedHashTags = new ArrayList<String>();
        expectedHashTags.add("#test1");
        expectedHashTags.add("#test2");
        assertThat(Lists.newArrayList(actualHashTags)).isEqualTo(expectedHashTags);
    }
}
