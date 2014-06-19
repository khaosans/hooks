package tweets;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.util.List;
import java.util.logging.Logger;

public class TwitterApplication {
    private final Logger logger = Logger.getLogger(TwitterApplication.class.getName());

    public static void main(String[] args) throws TwitterException {
//        new TwitterApplication().searchTweets();
//        StatusListener listener = new StatusListener() {
//            public void onStatus(Status status) {
//                System.out.println(status.getUser().getName() + " : " + status.getText());
//            }
//
//            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//            }
//
//            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
//            }
//
//            @Override
//            public void onScrubGeo(long l, long l2) {
//                System.out.println("Got scrub_geo event userId:" + l + " upToStatusId:" + l2);
//            }
//
//            @Override
//            public void onStallWarning(StallWarning stallWarning) {
//                System.out.println("Got stall warning:" + stallWarning);
//            }
//
//            public void onException(Exception ex) {
//                ex.printStackTrace();
//            }
//        };
//        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
//        twitterStream.addListener(listener);
//        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
//        twitterStream.sample();
    }

    private void publish() {
        String message = "Twitter application using Java http://www.java-tutorial.ch/architecture/twitter-with-java-tutorial";
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            try {
                RequestToken requestToken = twitter.getOAuthRequestToken();
                AccessToken accessToken = null;
                while (null == accessToken) {
                    logger.fine("Open the following URL and grant access to your account:");
                    logger.fine(requestToken.getAuthorizationURL());
                    try {
                        accessToken = twitter.getOAuthAccessToken(requestToken);
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            logger.severe("Unable to get the access token.");
                        } else {
                            te.printStackTrace();
                        }
                    }
                }
                logger.info("Got access token.");
                logger.info("Access token: " + accessToken.getToken());
                logger.info("Access token secret: " + accessToken.getTokenSecret());
            } catch (IllegalStateException ie) {
                // access token is already available, or consumer key/secret is not set.
                if (!twitter.getAuthorization().isEnabled()) {
                    logger.severe("OAuth consumer key/secret is not set.");
                    return;
                }
            }
            Status status = twitter.updateStatus(message);
            logger.info("Successfully updated the status to [" + status.getText() + "].");
        } catch (TwitterException te) {
            te.printStackTrace();
            logger.severe("Failed to get timeline: " + te.getMessage());
        }
    }

    public void searchTweets() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("khaosans");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }

    public String getTweets() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        String tweets = "";
        Query query = new Query("khaosans");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            tweets+=status.getUser().getScreenName() + ":" + status.getText()+"\n";
        }
        return tweets;
    }

    public void getTimeline() throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }
    }
}