package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import oauth.signpost.exception.OAuthException;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private static final int TWEET_LENGTH = 140;
  private static final double LONG_MIN = -180;
  private static final double LONG_MAX = 180;
  private static final double LAT_MIN = -90;
  private static final double LAT_MAX = 90;

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet){

    validatePostTweet(tweet);

    return (Tweet) dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {

    validateId(id);
    if(fields != null) {
      validateShowTweet(id, fields);
    }

    Tweet response = (Tweet) dao.findById(id);
    if(fields != null) {
      return testFields(response, fields);
    }
    else{
      return response;
    }

  }

  private Tweet testFields(Tweet response, String[] fields) {
    Tweet withoutNull = new Tweet();

    for(String field:fields){
      switch(field){
        case "created_at":
          withoutNull.setCreated_at(response.getCreated_at());
          break;
        case "id":
          withoutNull.setId(response.getId());
          break;
        case "id_str":
          withoutNull.setId_str(response.getId_str());
          break;
        case "text":
          withoutNull.setText(response.getText());
          break;
        case "entities":
          withoutNull.setEntities(response.getEntities());
          break;
        case "coordinates":
          withoutNull.setCoordinates(response.getCoordinates());
          break;
        case "retweet_count":
          withoutNull.setRetweet_count(response.getRetweet_count());
          break;
        case "favorite_count":
          withoutNull.setFavorite_count(response.getFavorite_count());
          break;
        case "favorited":
          withoutNull.setFavorited(response.isFavorited());
          break;
        case "retweeted":
          withoutNull.setRetweeted(response.isRetweeted());
          break;
      }
    }

    return withoutNull;
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    ArrayList<Tweet> totalDeletedTweets = new ArrayList<>();

    for(String id : ids){
      validateId(id);
      totalDeletedTweets.add((Tweet) dao.deleteById(id));
    }

    return totalDeletedTweets;
  }

  private void validatePostTweet(Tweet tweet) {

    if(tweet.getText().length() > TWEET_LENGTH){
      throw new IllegalArgumentException("Tweet should be max 140 characters");
    }

    if(tweet.getCoordinates().getCoordinates().get(0) > LONG_MAX ||
        tweet.getCoordinates().getCoordinates().get(0) < LONG_MIN ||
        tweet.getCoordinates().getCoordinates().get(1) > LAT_MAX  ||
        tweet.getCoordinates().getCoordinates().get(1) < LAT_MIN) {

      throw new IllegalArgumentException("Coordinates range: -180 < Longitude < 180 -90 < Latitude < 90");
    }

  }

  private void validateShowTweet(String id, String[] fields) {

    if (fields == null || fields.length == 0) {
      throw new IllegalArgumentException("There is no fields");
    }

    validateId(id);

    String[] tweetFields = new String[]{
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
    ArrayList<String> inputFieldsList = new ArrayList<>(Arrays.asList(fields));
    ArrayList<String> tweetFieldsList = new ArrayList<>(Arrays.asList(tweetFields));
    ArrayList<String> invalidFields = new ArrayList<>();

    for(String field : fields){
      if(!tweetFieldsList.contains(field)){
        invalidFields.add(field);
      }
    }

    if(invalidFields.size() != 0) {
      String invalidList = "";
      for (String inField : invalidFields) {
        invalidList += inField + " ";
      }

      throw new IllegalArgumentException(invalidList + "fields are invalid");
    }
}

  private void validateId(String id) {
    if(!id.matches("[0-9]+")){
      throw new IllegalArgumentException("Only use numerical characters for id");
    }
  }





}
