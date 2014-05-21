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

    static java.lang.String MY_ACCESS_TOKEN = "CAACEdEose0cBAKA3dEuZAXoh9vFlI3ulmt04fw1qwE4lLLOFQ9fyD2G4ZAZBo8Ua0gtDsty" +
            "xVmrvVDzEkw8z1CuZB9NKeBNmHT9GRIzBRIOiqUHjYWbvaroKZBYNCokxSL8uVCuDe7OPNhSDCzNLigTmYZBo0HSBDjVkYeOHDuV9N1Jm" +
            "inGZBZCkbyuXv6C0tcEZD";

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
