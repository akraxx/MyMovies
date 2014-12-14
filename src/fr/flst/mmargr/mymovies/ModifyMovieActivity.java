package fr.flst.mmargr.mymovies;

import fr.flst.mmargr.mymovies.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class ModifyMovieActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addmovie_history);
		TextView tv = (TextView) findViewById(R.id.add_movie);
		tv.setText("Modify data");
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String movieTitle = extras.getString("title");
		    String movieDate = extras.getString("year");
		    String movieRate = extras.getString("rate");
		    String movieNote = extras.getString("note");
		    
		    TextView t = (TextView) findViewById(R.id.title_edittext);
			t.setText(movieTitle);
			TextView n = (TextView) findViewById(R.id.note_edittext);
			n.setText(movieNote);
			TextView y = (TextView) findViewById(R.id.year_edittext);
			y.setText(movieDate);
			RatingBar r = (RatingBar) findViewById(R.id.rate);
			r.setProgress(Integer.valueOf(movieRate));
			
			RadioGroup rg = (RadioGroup)findViewById(R.id.radiogroup_addmovie);
			rg.setVisibility(View.GONE);
			
			Button button = (Button) findViewById(R.id.addMovie_button);
			button.setOnClickListener(new ModifyButtonListener(this));
			
		}
	}
	
	
}
