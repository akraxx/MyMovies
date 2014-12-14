package fr.flst.mmargr.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import fr.flst.mmargr.mymovies.R;
import fr.flst.mmargr.mymovies.main.DictionaryOpenHelper;

public class MovieSheetToWatchButtonListener implements View.OnClickListener{
	private MovieToWatchSheetActivity activity;
	private String type = "";
	
	public MovieSheetToWatchButtonListener(MovieToWatchSheetActivity activity, String type){
		this.activity = activity;
		this.type = type;
	}
	
	@Override
	public void onClick(View v) {
		TextView title = (TextView) this.activity.findViewById(R.id.movieTitle);
		String titleStr = title.getText().toString();
		TextView date = (TextView) this.activity.findViewById(R.id.movieDate);
		String dateStr = date.getText().toString();
		
		ContentValues values = new ContentValues();
		values.put(DictionaryOpenHelper.COLUMN_NAME_TYPE, this.type);
		values.put(DictionaryOpenHelper.COLUMN_NAME_TITLE, titleStr);
		values.put(DictionaryOpenHelper.COLUMN_NAME_YEAR, dateStr);
		values.put(DictionaryOpenHelper.COLUMN_NAME_NOTE, "");
		values.put(DictionaryOpenHelper.COLUMN_NAME_RATE, 0);
		

		DictionaryOpenHelper mDbHelper = new DictionaryOpenHelper(this.activity);
		SQLiteDatabase myDb = mDbHelper.getWritableDatabase();

		myDb.insert(DictionaryOpenHelper.TABLE_NAME, null, values);
		this.activity.finish();
		myToaster("Movie added to " + this.type);
	}
	
	public void myToaster(String text) {
		Context context = this.activity.getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
