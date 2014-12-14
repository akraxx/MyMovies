package fr.flst.mmargr.mymovies.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.Toast;

public class HttpGetAllMovies extends AsyncTask<String, Void, String>{
	private SearchMovieActivity searchMovieActivity;
	private String searchResult;
	
    public HttpGetAllMovies(SearchMovieActivity searchMovieActivity)
    {
       this.searchMovieActivity = searchMovieActivity;
    }

    @Override	
    protected String doInBackground(String... strings)
    {
    	StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        String title = strings[0].replace(" ", "%20");
        HttpGet httpGet = new HttpGet("http://www.omdbapi.com/?s=" + title);
        try {
          HttpResponse response = client.execute(httpGet);
          StatusLine statusLine = response.getStatusLine();
          int statusCode = statusLine.getStatusCode();
          if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
              builder.append(line);
            }
          } else {
        	  
          }
        } catch (ClientProtocolException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        this.searchResult =  builder.toString();
        return this.searchResult;
    }

    @Override
    protected void onPostExecute(String result)
    {
    	ArrayList<HashMap<String, String>> moviesList = new ArrayList<HashMap<String, String>>();
    	try {
    		JSONObject jsonResponse = new JSONObject(this.searchResult);
			JSONArray movies = jsonResponse.getJSONArray("Search");
	        HashMap<String, String> map;
			for(int j = 0; j < movies.length(); j++){
				JSONObject obj = (JSONObject) movies.get(j);
				map = new HashMap<String, String>();
				map.put("Title", obj.get("Title").toString());
				map.put("Year", obj.get("Year").toString());
				moviesList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			int duration = Toast.LENGTH_SHORT;
	    	Toast toast = Toast.makeText(this.searchMovieActivity.getApplicationContext(), "Movie not found or Internet not activated", duration);
	    	toast.show();
		}
    	this.searchMovieActivity.refresh(moviesList);
    }

}
