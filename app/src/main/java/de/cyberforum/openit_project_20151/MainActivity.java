package de.cyberforum.openit_project_20151;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import de.cyberforum.openit_project_20151.NewsImplementation.FetchMode;
import de.cyberforum.openit_project_20151.NewsInterface.FetchError0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsAction0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsItemRead0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsUIAction0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsUIReaction0;

import de.cyberforum.openit_project_20151.NewsImplementation.NewsUI;


public class MainActivity extends ActionBarActivity implements NewsUIReaction0 {

    protected NewsUI newsUI;

    public void update(FetchMode fetchMode, ArrayList<NewsItemRead0> news) {

    }

    public void displayError(FetchError0 fetchError) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) throws ClassCastException {
        super.onCreate(savedInstanceState);
        newsUI = new NewsUI(this);

        setContentView(R.layout.activity_main);

        FragmentTop5 fragmentTop5 = new FragmentTop5();
        fragmentTop5.setActivity(this);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment, fragmentTop5);
        ft.commit();

        NewsUIAction0 newsUIAction = (NewsUIAction0)newsUI;
        newsUIAction.activityChangeFragment(FetchMode.FM_TOP, fragmentTop5);

        NewsAction0 newsAction = (NewsAction0)newsUI;
        newsAction.get(FetchMode.FM_TOP, 5, 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
