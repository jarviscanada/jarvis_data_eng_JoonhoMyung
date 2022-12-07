package ca.jrvs.apps.twitter.dao.helper;


import java.io.IOException;
import java.net.URI;
import javax.validation.groups.Default;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@SuppressWarnings("Duplicates")
@Component
public class TwitterHttpHelper implements HttpHelper{

  private OAuthConsumer consumer;
  private HttpClient httpClient;



  /**
   * Constructor
   * Setup dependencies using secrets.
   *
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
      String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);

    httpClient = new DefaultHttpClient();
  }

  public TwitterHttpHelper(){
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);

    httpClient = new DefaultHttpClient();
  }

  @Override
  public HttpResponse httpPost(URI uri){
      try {
        return executeHttpRequest(HttpMethod.POST, uri, null);
      } catch (OAuthException | IOException e){
        throw new RuntimeException("Failed to execute");
      }
  }

  @Override
  public HttpResponse httpGet(URI uri){
      try {
        return executeHttpRequest(HttpMethod.GET, uri, null);
      } catch (OAuthException | IOException e) {
        throw new RuntimeException("Failed to execute");
      }
  }


  private HttpResponse executeHttpRequest(HttpMethod method, URI uri, StringEntity stringEntity)
      throws OAuthException, IOException {
    if (method == HttpMethod.GET) {
        HttpGet request = new HttpGet(uri);
        consumer.sign(request);
        return httpClient.execute(request);
    }
    else if (method == HttpMethod.POST) {
        HttpPost request = new HttpPost(uri);
        if (stringEntity != null) {
          request.setEntity(stringEntity);
        }
        consumer.sign(request);
        return httpClient.execute(request);
    }
    else {
        throw new IllegalArgumentException("Unknown HTTP method: " + method.name());
    }
  }

}
