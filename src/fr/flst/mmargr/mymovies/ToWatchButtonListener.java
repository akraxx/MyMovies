package fr.flst.mmargr.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import fr.flst.mmargr.mymovies.R;
import fr.flst.mmargr.mymovies.main.DictionaryOpenHelper;

public class ToWatchButtonListener implements View.OnClickListener{
private AddMovieActivity activity;
	
	public ToWatchButtonListener(AddMovieActivity activity){
		this.activity = activity;
	}
	
	@Override
	public void onClick(View view) {
		TextView title = (TextView) this.activity.findViewById(R.id.title_edittext_toWatch);
		String titleStr = title.getText().toString();
		TextView date = (TextView) this.activity.findViewById(R.id.year_edittext_toWatch);
		String dateStr = date.getText().toString();
		
		ContentValues values = new ContentValues();
		values.put(DictionaryOpenHelper.COLUMN_NAME_TYPE, "toWatch");
		values.put(DictionaryOpenHelper.COLUMN_NAME_TITLE, titleStr);
		values.put(DictionaryOpenHelper.COLUMN_NAME_YEAR, dateStr);
		values.put(DictionaryOpenHelper.COLUMN_NAME_NOTE, "");
		values.put(DictionaryOpenHelper.COLUMN_NAME_RATE, 0);
		

		DictionaryOpenHelper mDbHelper = new DictionaryOpenHelper(this.activity);
		SQLiteDatabase myDb = mDbHelper.getWritableDatabase();

		long test = myDb.insert(DictionaryOpenHelper.TABLE_NAME, null, values);
		Log.i("Monapp", test + " ");
		this.activity.finish();
		myToaster("Movie added to toWatch");
		
	}


	public void myToaster(String text) {
		Context context = this.activity;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
