import db.Posty;
import tweets.GetStatusTweet;

import java.sql.SQLException;
import java.util.concurrent.*;

/**
 * Created by souriyakhaosanga on 5/20/14.
 * Copyright © 2014 Urban Airship and Contributors
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, SQLException {
        Callable<String> callable = new GetStatusTweet();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(callable);

        System.out.println(future.get());

        new Posty();
        Posty.writeToDb(future.get());

    }

}
