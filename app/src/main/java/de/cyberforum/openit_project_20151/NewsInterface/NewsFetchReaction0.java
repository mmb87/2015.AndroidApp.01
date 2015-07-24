package de.cyberforum.openit_project_20151.NewsInterface;

import java.util.ArrayList;

import de.cyberforum.openit_project_20151.NewsImplementation.FetchMode;

/**
 * Created by Balzer on 24.07.2015.
 */
public interface NewsFetchReaction0 {
    void fetchResult(FetchMode mode, ArrayList<NewsItemRead0> newsList);
    void fetchError(FetchError0 fetchError);
}
