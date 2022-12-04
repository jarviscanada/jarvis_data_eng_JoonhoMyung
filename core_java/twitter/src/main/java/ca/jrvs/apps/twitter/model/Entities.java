package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "hashtags",
    "user_mentions"
})
public class Entities {

  @JsonProperty("hashtags")
  private List<Hashtag> hashtags;

  @JsonProperty("user_mentions")
  private List<UserMention> user_mentions;

  @JsonProperty("hashtags")
  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  @JsonProperty("hashtags")
  public void setHashtags(List<Hashtag> hashtags) {
    this.hashtags = hashtags;
  }

  @JsonProperty("user_mentions")
  public List<UserMention> getUser_mentions() {
    return user_mentions;
  }

  @JsonProperty("user_mentions")
  public void setUser_mentions(List<UserMention> user_mentions) {
    this.user_mentions = user_mentions;
  }

}
