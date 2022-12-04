package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void postTweet(){

    when(dao.create(any())).thenReturn(new Tweet());
    Tweet tweet = service.postTweet(TweetUtil.buildTweet("test", 50.0, 0.0));
    assertNotNull(tweet);
    assertNull(tweet.getCoordinates());
  }

  @Test
  public void showTweet(){
    String id = "1234567890";
    String[] fields = {
        "created_at",
        "id",
        "id_str",
        "text",
        "entities",
        "coordinates",
        "retweet_count",
        "favorite_count",
        "favorited",
        "retweeted"
    };

    when(dao.findById(any())).thenReturn(new Tweet());
    Tweet tweet = service.showTweet(id, fields);
    assertNotNull(tweet);
  }


  @Test
  public void deleteTweet(){
    String[] ids = {
        "1234567890",
        "1357924680"
    };
    when(dao.deleteById(any())).thenReturn(new Tweet());
    List<Tweet> tweets = service.deleteTweets(ids);
    assertNotNull(tweets);
  }
}
