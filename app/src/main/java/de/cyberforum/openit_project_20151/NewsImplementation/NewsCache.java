package de.cyberforum.openit_project_20151.NewsImplementation;

import java.util.ArrayList;

import de.cyberforum.openit_project_20151.NewsInterface.NewsItemRead0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsItemStatus0;

/**
 * Created by Balzer on 24.07.2015.
 */
public class NewsCache {

    protected int totalCount = 0;

    public NewsCache() {
        // create database if not existing
        // initialize if existing: totalCount
    }

    // total number of stored items
    public Integer getCount() { return totalCount; }

    // return list of { ID, modified } to mode
    public ArrayList<NewsItemStatus0> getIds(FetchMode mode, Integer itemCount, Integer itemOffset) {
        return null;
    }

    // return the actual news items
    public ArrayList<NewsItemRead0> getItems(ArrayList<NewsItemStatus0> newsIDList) {
        return null;
    }

    // store news items into cache
    public void store(ArrayList<NewsItemRead0> newsItems) {
    }
}
