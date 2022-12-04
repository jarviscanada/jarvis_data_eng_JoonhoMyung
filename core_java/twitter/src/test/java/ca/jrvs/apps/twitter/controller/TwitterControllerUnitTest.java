package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void postTweet(){
    when(service.postTweet(any())).thenReturn(new Tweet());
    String[] args = {"post", "test", "1d:-1d"};
    Tweet tweet = controller.postTweet(args);
    assertNotNull(tweet);
  }

  @Test
  public void showTweet(){
    when(service.showTweet(any(), any())).thenReturn(new Tweet());
    String[] args = {"show", "1353452346345213", "created_at,id_str"};
    Tweet tweet = controller.showTweet(args);
    assertNotNull(tweet);
  }

  @Test
  public void deleteTweet(){
    when(service.deleteTweets(any())).thenReturn(new ArrayList<>());
    String[] args = {"delete", "1353452346345213"};
    List<Tweet> tweet = controller.deleteTweet(args);
    assertNotNull(tweet);
  }
}
