
package de.cyberforum.openit_project_20151.NewsImplementation;

import de.cyberforum.openit_project_20151.NewsInterface.FetchError0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsFetchAction0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsFetchReaction0;
import de.cyberforum.openit_project_20151.NewsInterface.NewsItemRead0;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Balzer on 24.07.2015.
 */
public class NewsFetch implements NewsFetchAction0 {

    protected NewsFetchReaction0 newsFetchReaction;

    public NewsFetch(NewsFetchReaction0 newsFetchReaction) {
        this.newsFetchReaction = newsFetchReaction;
    }

    public FetchError0 initiateFetch(FetchMode fetchMode) {
        // null: request was queued successfully, otherwise: error object
        // TODO: check for network
        startFetch(fetchMode);
        return null;
    }

    protected ArrayList<AsyncHttpClient> clients;

    protected void startFetch(FetchMode fetchMode) {
        TextHttpResponseHandlerEx handler = new TextHttpResponseHandlerEx();
		handler.setOwnArguments(fetchMode, newsFetchReaction);

        AsyncHttpClient client = new AsyncHttpClient();
        // client.setBasicAuth("<login>", "<pwd>");
        client.get("http://www.cyberforum.de/cybermobile.php", handler);
    }

	protected class TextHttpResponseHandlerEx extends TextHttpResponseHandler {

        protected FetchMode fetchMode;
		protected NewsFetchReaction0 newsFetchReaction;

		public void setOwnArguments(FetchMode fetchMode, NewsFetchReaction0 newsFetchReaction) {
            this.fetchMode = fetchMode;
			this.newsFetchReaction = newsFetchReaction;
		}

		@Override
        public void onSuccess(String responseBody) {
            JSONArray response;

            try {
                response = new JSONArray(responseBody);
            } catch (JSONException e) {
                FetchError fetchError = new FetchError(fetchMode, "foo", "bar");
                newsFetchReaction.fetchError(fetchError);
                return;
            }

            int len = response.length();
            if (len > 0) {
                ArrayList<NewsItemRead0> newsItems = new ArrayList<>();
                for(int i = 0; i < len; i++) {
                    JSONObject line;
                    try {
                        line = response.getJSONObject(i);
                    } catch (JSONException e) {
                        break;
                    }

                    Integer id = null;
                    try {
                        id = line.getInt("newsId");
                    } catch (JSONException e) {
                        break;
                    }

                    NewsItem newsItem = new NewsItem(id, null, null);
                    newsItems.add(newsItem);
                }

                if (newsItems.size() > 0)
                    newsFetchReaction.fetchResult(fetchMode, newsItems);
            }
		}

        @Override
        public void onFailure(String responseBody, Throwable e) {
            FetchError fetchError = new FetchError(fetchMode, "foo", "bar");
            newsFetchReaction.fetchError(fetchError);
		}
	}

    protected class NewsItem implements NewsItemRead0 {
        NewsItem(Integer id, Date modified, Boolean valid) {
            this.id = id;
            this.modified = modified;
            this.valid = true;
        }
        protected Integer id;
        public Integer getId() { return id; }
        protected Date modified;
        public Date getModified() { return modified; }

        // not deleted and not before start-of-publication and not after end-of-publication
        protected Boolean valid;
        public Boolean getValid() { return valid; }

        public void NewsItemWrite(Date published, String title, String body) {
            this.published = published;
            this.title = title;
            this.body = body;
        }
        protected Date published;
        protected String title;
        protected String body;
        public Date getPublished() { return published; }
        public String getTitle() { return title; }      // plain text, UTF-8
        public String getBody() { return body; }       // HTML, UTF-8
    }
}
