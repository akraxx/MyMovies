package fr.flst.mmargr.mymovies;

import fr.flst.mmargr.mymovies.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddMovieActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
	
		if (extras != null) {
			 	String movieTitle = extras.getString("movieTitle");
			    String movieDate = extras.getString("movieDate");
			    setContentView(R.layout.addmovie_history);
			    EditText title = (EditText) findViewById(R.id.title_edittext);
			    title.setText(movieTitle);
			    EditText year = (EditText) findViewById(R.id.year_edittext);
			    year.setText(movieDate);
			    if(extras.getBoolean("radioGroup") == false){
					RadioGroup rg = (RadioGroup)findViewById(R.id.radiogroup_addmovie);
					rg.setVisibility(View.GONE);
			    }
			    if(extras.getString("toDelete") != null){
					Button button = (Button) findViewById(R.id.addMovie_button);
				    button.setOnClickListener(new HistoryFromWatchButtonListener(this));
			    }else{
			    	Button button = (Button) findViewById(R.id.addMovie_button);
				    button.setOnClickListener(new HistoryButtonListener(this));
			    }
		}else{
			setContentView(R.layout.addmovie_towatch);
			Button buttonTW = (Button) findViewById(R.id.addMovie_button);
			buttonTW.setOnClickListener(new ToWatchButtonListener(this));
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();
		Button button = (Button) findViewById(R.id.addMovie_button);
		
		switch (view.getId()) {
		case R.id.radio_towatch:
			if (checked)
				setContentView(R.layout.addmovie_towatch);
				button.setOnClickListener(new ToWatchButtonListener(this));
			break;
			
		case R.id.radio_history:
			if (checked)
				setContentView(R.layout.addmovie_history);
				button.setOnClickListener(new HistoryButtonListener(this));
			break;
		}
	}

}
