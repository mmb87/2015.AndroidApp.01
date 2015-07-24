package de.cyberforum.openit_project_20151.NewsInterface;

import android.app.Fragment;

import java.util.ArrayList;

import de.cyberforum.openit_project_20151.NewsImplementation.FetchMode;
import de.cyberforum.openit_project_20151.NewsInterface.FetchError0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsItemRead0;

/**
 * Created by Balzer on 24.07.2015.
 */
public interface NewsUIReaction0 {
    void update(FetchMode mode, ArrayList<NewsItemRead0> newsItems);
    void displayError(FetchError0 fetchError);
}
