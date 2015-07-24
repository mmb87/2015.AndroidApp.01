package de.cyberforum.openit_project_20151.NewsInterface;

import java.util.ArrayList;

import de.cyberforum.openit_project_20151.NewsImplementation.FetchMode;

/**
 * Created by Balzer on 24.07.2015.
 */
public interface NewsAction0 {
    ArrayList<NewsItemRead0> get(FetchMode fetchmode, Integer itemCount, Integer itemOffset);
}
