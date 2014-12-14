package fr.flst.mmargr.mymovies.search;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import fr.flst.mmargr.mymovies.R;
import fr.flst.mmargr.mymovies.MovieToWatchSheetActivity;

public class SearchMovieActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_movie);
		
		Button button = (Button) findViewById(R.id.searchMovie_button);
		
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	EditText movieTitle = (EditText) findViewById(R.id.searchMovie_title_edittext);
            	String queryText = movieTitle.getText().toString();
            	HttpGetAllMovies task = new HttpGetAllMovies(SearchMovieActivity.this);
            	task.execute(queryText);
            }
        });
	}
	
	@Override
	protected void onPause(){
	    super.onPause();
	}

	public void refresh(ArrayList<HashMap<String, String>> moviesList) {
		SimpleAdapter adapter = new SimpleAdapter (this.getBaseContext(), moviesList, R.layout.movie_search,
	               new String[] {"Title", "Year"}, new int[] {R.id.titleResult, R.id.dateResult});
		final ListView lV = (ListView) findViewById(R.id.searchMoviesList); 
 		lV.setAdapter(adapter);
 		
 		lV.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
				
				@SuppressWarnings("unchecked")
				HashMap<String, String> selectedItem = (HashMap<String, String>) lV.getItemAtPosition(myItemInt);
				Intent i = new Intent(getApplicationContext(), MovieToWatchSheetActivity.class);
				String queryTitle = selectedItem.get("Title");
				String queryDate = selectedItem.get("Year");
				i.putExtra("movieTitle", queryTitle);
				i.putExtra("movieDate", queryDate);
				i.putExtra("buttonToWatch", 1);
				startActivity(i);
				
			}
		});
	}

}
