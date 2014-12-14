package fr.flst.mmargr.mymovies.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import fr.flst.mmargr.mymovies.MovieToWatchSheetActivity;

public class HttpGetAMovie extends AsyncTask<String, Void, String>{
	private MovieToWatchSheetActivity movieSheetActivity;
	private String searchResult;
	
	public HttpGetAMovie(MovieToWatchSheetActivity movieSheetActivity){
		this.movieSheetActivity = movieSheetActivity;
	}
	
	@Override
	protected String doInBackground(String... strings) {
		
		StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        String title = strings[0].replace(" ", "%20");
        String date = strings[1].replace(" ", "%20");
        HttpGet httpGet = new HttpGet("http://www.omdbapi.com/?t=" + title + "&y=" + date);
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
    	try {
    		JSONObject jsonResponse = new JSONObject(this.searchResult);
			
	        HashMap<String, String> map;
			map = new HashMap<String, String>();
			map.put("Describe", jsonResponse.getString("Plot"));
			map.put("Image", jsonResponse.getString("Poster"));
			this.movieSheetActivity.refresh(map);
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

}
