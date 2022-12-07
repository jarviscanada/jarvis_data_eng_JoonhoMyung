package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  /**
   * Parse user argument and post a tweet by calling service classes
   *
   * @param args
   * @return a posted tweet
   * @throws IllegalArgumentException if args are invalid
   */
  @Override
  public Tweet postTweet(String[] args) {

    if(args.length != 3 || !args[2].contains(":")){
      throw new IllegalArgumentException("There should be 3 arguments which are "
          + "\"TwitterApp post, text, latitude:longitude\"");
    }

    String text = args[1];
    String[] lonLatSplit = args[2].split(COORD_SEP);

    if(lonLatSplit.length != 2 || StringUtils.isEmpty(text)){
      throw new IllegalArgumentException("Latitude and Longitude should be in the form of "
          + "latitude:longitude.");
    }

    List<Double> lonLatDoubleConversion = new ArrayList<>();
    try {
      lonLatDoubleConversion.add(Double.parseDouble(lonLatSplit[0]));
      lonLatDoubleConversion.add(Double.parseDouble(lonLatSplit[1]));
    } catch (Exception e){
      throw new IllegalArgumentException("Invalid arguments format. "
          + "There should be 3 arguments which are"
          + "\"TwitterApp post, text, latitude:longitude\". "
          + "Lat and Long need to be in form of double");
    }

    Tweet tweet = TweetUtil.buildTweet(text, lonLatDoubleConversion.get(1),
        lonLatDoubleConversion.get(0));

    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {

    if(args.length != 2 && args.length != 3){
      throw new IllegalArgumentException("Invalid format:  "
          + "\"TwitterApp show, id, [field1,field2,...] should be provided\"");
    }

    String id = args[1];
    String[] fields = null;
    if(args.length > 2) {
      fields = args[2].split(COMMA);
    }
    return service.showTweet(id, fields);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if(args.length != 2){
      throw new IllegalArgumentException("Invalid format:  "
          + "\"TwitterApp delete, [id1,id2,...] should be provided\"");
    }

    String[] ids = args[1].split(COMMA);

    return service.deleteTweets(ids);
  }
}
