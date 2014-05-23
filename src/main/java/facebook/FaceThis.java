package facebook;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import com.restfb.types.User;

import java.util.List;

/**
 * Created by souriyakhaosanga on 5/20/14.
 * Copyright © 2014 Urban Airship and Contributors
 */
public class FaceThis {

    static java.lang.String MY_ACCESS_TOKEN = "CAACEdEose0cBAEvQGPqoi8gMhp9J9bofE8lCCvWZBZABOaN8uEloVWZCz6tTkElq8UFaZCNvbpT586C8ROZBkV6GybCjt64ZAyNhlPwMuzneyL64As29PwonL9Fn38iKsAVvTaOZCj7KtRnVo5Cy7v4NupTZA2dwXyKx3TLhYpcCZBjksVQS6UUZBuNTmQy3JU6aGsYSK1ZCPG5GAZDZD";
    private static FacebookClient facebookClient;

    public static void main(String[] args) {
        facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN);
        FacebookClient publicOnlyFacebookClient = new DefaultFacebookClient();

        //added for extra security
        //FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, MY_APP_SECRET);

        User user = facebookClient.fetchObject("me", User.class);

        System.out.println("User name: " + user.getName());
        getFriendShit();
    }

    public static void getFriendShit() {
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
        Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);

        System.out.println("Count of my friends: " + myFriends.getData().size());
        System.out.println("First item in my feed: " + myFeed.getData().get(0));


        for (List<Post> myFeedConnectionPage : myFeed)
            for (Post post : myFeedConnectionPage)
                System.out.println(("Post: " + post));
    }

}
