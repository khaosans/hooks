package tweets;

import java.util.concurrent.Callable;

/**
 * Created by solus on 5/22/14.
 */
public class GetStatusTweet implements Callable<String>{
    @Override
    public String call() throws Exception {
        TwitterApplication tweets = new TwitterApplication();
        return tweets.getTweets();
    }
}
