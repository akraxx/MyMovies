package fr.flst.mmargr.mymovies;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.flst.mmargr.mymovies.R;
import fr.flst.mmargr.mymovies.search.DownloadImageTask;
import fr.flst.mmargr.mymovies.search.HttpGetAMovie;

public class MovieToWatchSheetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_sheet_towatch);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String movieTitle = extras.getString("movieTitle");
		    String movieDate = extras.getString("movieDate");
		    
		    TextView t = (TextView) findViewById(R.id.movieTitle);
			t.setText(movieTitle);
			TextView d = (TextView) findViewById(R.id.movieDate);
			d.setText(movieDate);
			
			HttpGetAMovie task = new HttpGetAMovie(this);
        	task.execute(movieTitle, movieDate);
        	
        	Button buttonH = (Button) findViewById(R.id.history);
        	if(extras.getString("toDelete") != null){
        		buttonH.setOnClickListener(new MovieSheetHistoryButtonListener(this, Boolean.TRUE, movieTitle, movieDate));
        	}else{
        		buttonH.setOnClickListener(new MovieSheetHistoryButtonListener(this, Boolean.FALSE, movieTitle, movieDate));
        	}
		}
		
		if(extras.getInt("buttonToWatch") == 0){
			Button button = (Button) findViewById(R.id.toWatch);
			button.setVisibility(View.GONE);
			button.setOnClickListener(new MovieSheetToWatchButtonListener(this, "toWatch"));   
  		}else{
			Button button = (Button) findViewById(R.id.toWatch);
			button.setOnClickListener(new MovieSheetToWatchButtonListener(this, "toWatch"));
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	public void refresh(HashMap<String, String> data){
		TextView t = (TextView) findViewById(R.id.movieDescribe);
		t.setText(data.get("Describe"));
		new DownloadImageTask((ImageView) findViewById(R.id.movieImage))
        .execute(data.get("Image"));
	}
}
