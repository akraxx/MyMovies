package fr.flst.mmargr.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import fr.flst.mmargr.mymovies.R;
import fr.flst.mmargr.mymovies.main.DictionaryOpenHelper;
import fr.flst.mmargr.mymovies.main.MoviesListsActivity;

public class HistoryButtonListener implements View.OnClickListener{
	private AddMovieActivity activity;
	
	public HistoryButtonListener(AddMovieActivity activity){
		this.activity = activity;
	}
	
	@Override
	public void onClick(View v) {

		EditText title = (EditText) this.activity.findViewById(R.id.title_edittext);
		String titleStr = title.getText().toString();

		EditText year = (EditText) this.activity.findViewById(R.id.year_edittext);
		String yearStr = year.getText().toString();
		
		EditText note = (EditText) this.activity.findViewById(R.id.note_edittext);
		String noteStr = note.getText().toString();
		
		String typeStr = "history";

		RatingBar rate = (RatingBar) this.activity.findViewById(R.id.rate);
		int rated = rate.getProgress();

		if (titleStr.equals("")) {
			myToaster("You must enter the title of a film");
		} else if (noteStr.equals("")) {
			myToaster("You must write something about the film");
		} else if (rated == 0) {
			myToaster("Please rate the film");
		} else if (typeStr.equals("")) {
			myToaster("toWatch or History ?");
		} else {
			ContentValues values = new ContentValues();
			values.put(DictionaryOpenHelper.COLUMN_NAME_TYPE, typeStr);
			values.put(DictionaryOpenHelper.COLUMN_NAME_TITLE, titleStr);
			values.put(DictionaryOpenHelper.COLUMN_NAME_YEAR, yearStr);
			values.put(DictionaryOpenHelper.COLUMN_NAME_NOTE, noteStr);
			values.put(DictionaryOpenHelper.COLUMN_NAME_RATE, rated);
			
			DictionaryOpenHelper mDbHelper = new DictionaryOpenHelper(this.activity);
			SQLiteDatabase myDb = mDbHelper.getWritableDatabase();
			myDb.delete(DictionaryOpenHelper.TABLE_NAME, DictionaryOpenHelper.COLUMN_NAME_TITLE + "=" + "'" + titleStr + "'", null);
			myDb.insert(DictionaryOpenHelper.TABLE_NAME, null, values);
			Intent myIntent = new Intent(this.activity,
					MoviesListsActivity.class);
			this.activity.startActivity(myIntent);
			this.activity.finish();
			myToaster("Movie added to " + typeStr);
		
		}
	}
	

	public void myToaster(String text) {
		Context context = this.activity.getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}