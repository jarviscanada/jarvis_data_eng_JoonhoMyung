package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import net.bytebuddy.pool.TypePool.Resolution.Illegal;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  String hashTag = "#Qatar2022";

  String text = "@Juno_Myung Let's go Korea! " + hashTag;

  Double lat = 1d;
  Double lon = -1d;

  private TwitterDao dao;
  public TwitterService service;
  public TwitterController controller;

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
    this.service = new TwitterService(dao);
    this.controller = new TwitterController(service);
  }

  @Test
  public void postTweet(){

    String invalidLonLat = (lon + "-" + lat);
    String invalidLonLat2 = ("not:double");
    String invalidLonLat3 = ("1.0:2.0:3.0");
    String[] args = {"post", text, invalidLonLat};
    String[] invalidArgs = {"post", text, invalidLonLat, "invalid"};
    String[] invalidArgs2 = {"post", text, invalidLonLat2};
    String[] invalidArgs3 = {"post", text, invalidLonLat3};


    //check if there is no ":" between latitude and longitude
    try {
      controller.postTweet(args);
      fail();
    } catch (IllegalArgumentException e) {
      /**
      assertEquals("Invalid arguments format."
          + "There should be 3 arguments which are"
          + "\"TwitterApp post, text, latitude:longitude\"", e.getMessage());
      */

      assertEquals("There should be 3 arguments which are "
          + "\"TwitterApp post, text, latitude:longitude\"", e.getMessage());;
    }

    //check if there is more or less than 3 arguments
    try {
      controller.postTweet(invalidArgs);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("There should be 3 arguments which are "
          + "\"TwitterApp post, text, latitude:longitude\"", e.getMessage());;
    }


    try {
      controller.postTweet(invalidArgs3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Latitude and Longitude should be in the form of "
          + "latitude:longitude.", e.getMessage());
    }


    try {
      controller.postTweet(invalidArgs2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arguments format. "
          + "There should be 3 arguments which are"
          + "\"TwitterApp post, text, latitude:longitude\". "
          + "Lat and Long need to be in form of double", e.getMessage());
    }

    long timeInMillis = System.currentTimeMillis();
    String validLonLat = (lon + ":" + lat);
    String[] validArgs = {"post", text + " " + timeInMillis, validLonLat};

    Tweet checkTweet = controller.postTweet(validArgs);

    assertEquals(text + " " + timeInMillis, checkTweet.getText());
    assertEquals(lon, checkTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lat, checkTweet.getCoordinates().getCoordinates().get(0));
    assertTrue(hashTag.contains(checkTweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void showTweet() {

    String id = "1600619311936282624";
    String fields = "text,id_str,coordinates,entities";
    String[] invalidArgs = {"show", id, fields, "extra"};

    try {
      controller.showTweet(invalidArgs);
      fail();
    } catch (Exception e){
      assertEquals("Invalid format:  "
          + "\"TwitterApp show, id, [field1,field2,...] should be provided\"", e.getMessage());
    }

    String[] validArgs = {"show", id, fields};
    Tweet checkTweet = controller.showTweet(validArgs);

    assertEquals(text + " 1670452355122" , checkTweet.getText());
    assertEquals(lon, checkTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lat, checkTweet.getCoordinates().getCoordinates().get(0));
    assertTrue(hashTag.contains(checkTweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void deleteTweet() {

    String id = "1598470860658163712";
    String[] invalidArgs = {"delete", id, "text"};

    try {
      controller.deleteTweet(invalidArgs);
      fail();
    } catch (Exception e){
      assertEquals("Invalid format:  "
          + "\"TwitterApp delete, [id1,id2,...] should be provided\"", e.getMessage());
    }

    String validLonLat = (lon + ":" + lat);
    long timeInMillis = System.currentTimeMillis();
    String[][] validArgs = new String[4][2];
    String ids = "";

    for(int i = 0; i < validArgs.length; i++){
      validArgs[i] = new String[]{"post", text + " test" + i + " "
          + timeInMillis, validLonLat};
      Tweet postTweet = controller.postTweet(validArgs[i]);
      ids += postTweet.getId_str();
      if(i != validArgs.length-1){
        ids+=",";
      }
    }

    String[] validArgsDelete = {"delete", ids};
    List<Tweet> checkTweet = controller.deleteTweet(validArgsDelete);

    assertEquals(4, checkTweet.size());
  }

}
