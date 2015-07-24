package de.cyberforum.openit_project_20151.NewsInterface;

import java.util.ArrayList;

import de.cyberforum.openit_project_20151.NewsImplementation.FetchMode;

/**
 * Created by Balzer on 24.07.2015.
 */
public interface NewsCacheAction0 {
    Integer getCount();
    ArrayList<NewsItemStatus0> getIds(FetchMode mode, Integer itemCount, Integer itemOffset);
    ArrayList<NewsItemRead0> getItems(ArrayList<NewsItemStatus0> newsIDList);
    void store(ArrayList<NewsItemRead0> newsItems);
}
