package fr.flst.mmargr.mymovies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;
import fr.flst.mmargr.mymovies.main.DictionaryOpenHelper;

public class MovieSheetDeleteButtonListener implements View.OnClickListener{
	private MovieToWatchSheetActivity activity;
	private String title = "";
	
	public MovieSheetDeleteButtonListener(MovieToWatchSheetActivity movieToWatchSheetActivity, String title, String year) {
		this.activity = movieToWatchSheetActivity;
		this.title = title;
	}

	@Override
	public void onClick(View v) {
		// SUPPRESSION DE LA BASE DE DONNEES ET REDIRECTION VERS LA PREMIERE ACTIVITE
		DictionaryOpenHelper mDbHelper = new DictionaryOpenHelper(this.activity);
		SQLiteDatabase myDb = mDbHelper.getWritableDatabase();
		myDb.delete(DictionaryOpenHelper.TABLE_NAME, DictionaryOpenHelper.COLUMN_NAME_TITLE + "=" + '"' + this.title +'"' +  " AND " + 
													DictionaryOpenHelper.COLUMN_NAME_RATE + "=0", null);
		this.activity.finish();
		myToaster("Movie " + this.title +" removed to toWatch");
		
	}
	
	public void myToaster(String text) {
		Context context = this.activity;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
