package de.cyberforum.openit_project_20151.NewsImplementation;

import java.util.ArrayList;

import de.cyberforum.openit_project_20151.NewsInterface.FetchError0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsAction0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsFetchAction0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsFetchReaction0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsItemRead0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsItemStatus0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsUIReaction0;

/**
 * Created by Balzer on 24.07.2015.
 */
public class NewsFactory implements NewsAction0, NewsFetchReaction0 {
    protected NewsCache newsCache;
    protected NewsFetch newsFetch;
    protected NewsFetchAction0 newsFetchAction;

    protected NewsUIReaction0 newsUIReaction;

    public NewsFactory(NewsUIReaction0 newsUIReaction) throws ClassCastException {
        this.newsUIReaction = newsUIReaction;
        newsCache = new NewsCache();
        newsFetch = new NewsFetch(this);
        newsFetchAction = (NewsFetchAction0)newsFetch;
    }

    public ArrayList<NewsItemRead0> get(FetchMode fetchMode, Integer itemCount, Integer itemOffset) {

        // read from cache
        ArrayList<NewsItemRead0> cachedData = null;
        ArrayList<NewsItemStatus0> cachedDataStatus = null;
        if(newsCache.getCount()>0){
            cachedDataStatus = newsCache.getIds(fetchMode, newsCache.getCount(), null); //I do not know what ItemOffset means
            cachedData = newsCache.getItems(cachedDataStatus);
        }

        // initiate fetch
        newsFetchAction.initiateFetch(fetchMode);

        return cachedData;
    }

    public void fetchResult(FetchMode fetchmode, ArrayList<NewsItemRead0> newsList) {
        // store in cache
        newsCache.store(newsList);
        // send update to UI
        newsUIReaction.update(fetchmode, newsList);
    }

    public void fetchError(FetchError0 fetchError) {
        // depending on error object determine further course of action
        newsUIReaction.displayError(fetchError);
    }

}
