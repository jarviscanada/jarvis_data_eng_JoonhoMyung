package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import oauth.signpost.exception.OAuthException;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

  String hashTag = "#Qatar2022";

  String text = "@Juno_Myung Let's go Korea! " + hashTag;

  Double lat = 1d;
  Double lon = -1d;

  private TwitterDao dao;
  public TwitterService service;

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
    service = new TwitterService(dao);
  }

  @Test
  public void postTweet() {

    String invalidText = "Lorem Ipsum is simply dummy text of the printing and typesetting "
        + "industry. Lorem Ipsum has been the industry's standard dummy text ever since the"
        + " 1500s, when an unknown printer took a galley of type and scrambled it to make a "
        + "type specimen book. It has survived not only five centuries, but also the leap into"
        + " electronic typesetting, remaining essentially unchanged.";
    double invalidLon = -200;
    double invalidLat = 100;


    try{
      service.postTweet(TweetUtil.buildTweet(invalidText, lon, lat));
      fail();
    } catch (IllegalArgumentException e){
        assertEquals("Tweet should be max 140 characters", e.getMessage());
    }

    try{
      service.postTweet(TweetUtil.buildTweet(text, invalidLon, lat));
    } catch (IllegalArgumentException e){
      assertEquals("Coordinates range: -180 < Longitude < 180 -90 < Latitude < 90", e.getMessage());
    }

    try{
      service.postTweet(TweetUtil.buildTweet(text, lon, invalidLat));
    } catch (IllegalArgumentException e){
      assertEquals("Coordinates range: -180 < Longitude < 180 -90 < Latitude < 90", e.getMessage());
    }

    long timeInMillis = System.currentTimeMillis();
    Tweet postTweet = TweetUtil.buildTweet(text + " " + timeInMillis, lon, lat);
    Tweet validTweet = service.postTweet(postTweet);



    assertEquals(text + " " + timeInMillis, validTweet.getText());
    assertEquals(lon, validTweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, validTweet.getCoordinates().getCoordinates().get(1));
    assertTrue(hashTag.contains(validTweet.getEntities().getHashtags().get(0).getText()));

  }


  @Test
  public void showTweet() {
    String id = "1601413329095999488";
    String invalidId = "JOON1472";

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

    String[] invalidFields = {
        "tweet_number",
        "nationality",
        "age"
    };

    String invalidOutput = "";

    for(String s: invalidFields) {
      invalidOutput += s + " ";
    }

    try{
      service.showTweet(id, invalidFields);
      fail();
    } catch(IllegalArgumentException e) {
        assertEquals( invalidOutput + "fields are invalid", e.getMessage());
    }

    try{
      service.showTweet(invalidId, fields);
      fail();
    } catch(IllegalArgumentException e) {
      assertEquals("Only use numerical characters for id", e.getMessage());
    }


    Tweet validTweet = service.showTweet(id, fields);

    String expectedText = "@Juno_Myung Let's go Korea! #Qatar2022 1670641663852";

    try {
      System.out.println(JsonUtil.toJson(validTweet, true, false));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert tweet object to string", e);
    }

    assertEquals(expectedText, validTweet.getText());
    assertEquals(lon, validTweet.getCoordinates().getCoordinates().get(0));
    assertEquals(lat, validTweet.getCoordinates().getCoordinates().get(1));
    assertTrue(hashTag.contains(validTweet.getEntities().getHashtags().get(0).getText()));
  }


  @Test
  public void deleteTweets() {
    String[] invalidId = {"JOON", "HO", "MYUNG"};


    try {
      service.deleteTweets(invalidId);
      fail();
    } catch (IllegalArgumentException e){
      assertEquals("Only use numerical characters for id", e.getMessage());
    }

    long timeInMillis = System.currentTimeMillis();
    Tweet tweet = service.postTweet(TweetUtil.buildTweet(text + " " +timeInMillis, lon, lat));
    String[] id = {tweet.getId_str()};

    List<Tweet> deletedTweets = service.deleteTweets(id);

    for(Tweet t : deletedTweets){
      assertEquals(text+ " " +timeInMillis, t.getText());
      assertEquals(lon, t.getCoordinates().getCoordinates().get(0));
      assertEquals(lat, t.getCoordinates().getCoordinates().get(1));
      assertTrue(hashTag.contains(t.getEntities().getHashtags().get(0).getText()));
    }


  }
}
