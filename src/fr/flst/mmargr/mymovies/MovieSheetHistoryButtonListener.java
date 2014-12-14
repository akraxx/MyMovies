package fr.flst.mmargr.mymovies;

import android.content.Intent;
import android.view.View;

public class MovieSheetHistoryButtonListener implements View.OnClickListener {
	private MovieToWatchSheetActivity activity;
	private Boolean delete = Boolean.FALSE;
	private String movieTitle = "";
	private String movieYear = "";
	
	public MovieSheetHistoryButtonListener(MovieToWatchSheetActivity activity, Boolean delete, String title, String year){
		this.activity = activity;
		this.delete = delete;
		this.movieTitle = title;
		this.movieYear = year;
	}
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent(this.activity, AddMovieActivity.class);
		i.putExtra("radioGroup", false);
		i.putExtra("movieTitle", this.movieTitle);
		i.putExtra("movieDate", this.movieYear);
		if(this.delete){
			i.putExtra("toDelete", "yes");
		}
		this.activity.startActivity(i);
		this.activity.finish();
	}

}
