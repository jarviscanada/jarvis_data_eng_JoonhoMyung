package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {


  String hashTag = "#WeTheNorth";
  String text = "@Juno_Myung Let's go Raptors! " + hashTag + " " + System.currentTimeMillis();
  Double lat = 1d;
  Double lon = -1d;
  private TwitterDao dao;


  @Before
  public void setup(){
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);

    this.dao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() throws Exception {


    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    Tweet tweet = dao.create(postTweet);

    assertEquals(text, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
    System.out.println(JsonUtil.toJson(tweet, true, false));
  }

  @Test
  public void findById(){
    String id_str = "1600620082853740544";
    String expectedText = "@Juno_Myung Let's go Raptors! " + hashTag + " 1670452538780";
    Tweet tweet = dao.findById(id_str);


    assertEquals(expectedText, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, tweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, tweet.getCoordinates().getCoordinates().get(1));
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void deleteById() throws IOException {
    //String id_str = "";
    Tweet postTweet = TweetUtil.buildTweet(text + " test", lon, lat);
    Tweet tweet = dao.create(postTweet);
    Tweet delTweet = dao.deleteById(tweet.getId_str());

    assertEquals(text+ " test", delTweet.getText());
    assertNotNull(delTweet.getCoordinates());
    assertEquals(2, delTweet.getCoordinates().getCoordinates().size());
    assertEquals(lon, delTweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, delTweet.getCoordinates().getCoordinates().get(1));
    assertTrue(hashTag.contains(delTweet.getEntities().getHashtags().get(0).getText()));
  }
}
