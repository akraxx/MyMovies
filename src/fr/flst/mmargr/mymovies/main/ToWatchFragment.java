package fr.flst.mmargr.mymovies.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import fr.flst.mmargr.mymovies.R;
import fr.flst.mmargr.mymovies.MovieToWatchSheetActivity;
import fr.flst.mmargr.mymovies.ScheduleActivity;

public class ToWatchFragment extends Fragment{
	public static final String ARG_SECTION_NUMBER = "section_number";
	private DictionaryOpenHelper mDbHelper;
	
    public ToWatchFragment() {
    }

    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);
 		this.mDbHelper = new DictionaryOpenHelper(getActivity());
 		this.behavior();
    }  
    
    @Override
	public void onResume() {
	    super.onResume();
	    this.behavior();
	}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.towatch_fragment, container, false);
        return rootView;
    }
    
    public void behavior(){
    	SQLiteDatabase myDb = this.mDbHelper.getReadableDatabase();
 		String whereClause = "type = 'toWatch'";
 		
 		Cursor c = myDb.query(DictionaryOpenHelper.TABLE_NAME, new String[] {
 				"_id", "title", "note", "year", "rate" }, whereClause, null, null, null, null);

 		// ADAPTATION A LA LISTVIEW
 		String[] from = { "title", "year"};
 		int[] to = { R.id.titleResult, R.id.dateResult };
 		
 		
 		final ListView lV = (ListView) getActivity().findViewById(R.id.towatchFragmentView); 
 		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),R.layout.movie_towatch, c, from, to, 0);
 		lV.setAdapter(adapter);
 		
 		lV.setLongClickable(true);
 		lV.setOnItemLongClickListener(new OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parentView, View childView, final int position, long id) {
            	new AlertDialog.Builder(getActivity()).setTitle("Remove")
    		    .setMessage("What do you want to do ?")
    		    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
    		        public void onClick(DialogInterface dialog, int which) { 
    		        	SQLiteDatabase myDb = mDbHelper.getWritableDatabase();
    	            	SQLiteCursor selectedItem = (SQLiteCursor) lV.getItemAtPosition(position);
    		        	String titleStr = selectedItem.getString(selectedItem.getColumnIndex("title"));
    	    			myDb.delete(DictionaryOpenHelper.TABLE_NAME, DictionaryOpenHelper.COLUMN_NAME_TITLE + "=" + "'" + titleStr + "'", null);
    	    			getActivity().recreate();
    		        }
    		     })
    		    .setNegativeButton("Schedule", new DialogInterface.OnClickListener() {
    		        public void onClick(DialogInterface dialog, int which) { 
    		        	Intent i = new Intent(getActivity(), ScheduleActivity.class);
    		        	SQLiteCursor selectedItem = (SQLiteCursor) lV.getItemAtPosition(position);
    		        	String titleStr = selectedItem.getString(selectedItem.getColumnIndex("title"));
    		        	String yearStr = selectedItem.getString(selectedItem.getColumnIndex("year"));
    		        	i.putExtra("movieTitle", titleStr);
    		    		i.putExtra("movieYear", yearStr);
    		        	getActivity().startActivity(i);
    		        }
    		     })
    		     .show();
                return false;
            }
        });
 		
 		lV.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int myItemInt, long mylng) {
				SQLiteCursor selectedItem = (SQLiteCursor) lV.getItemAtPosition(myItemInt);
				Intent i = new Intent(getActivity().getApplicationContext(), MovieToWatchSheetActivity.class);
				String queryTitle = selectedItem.getString(selectedItem.getColumnIndex("title"));
				String queryDate = selectedItem.getString(selectedItem.getColumnIndex("year"));
				i.putExtra("movieTitle", queryTitle);
				i.putExtra("movieDate", queryDate);
				i.putExtra("toDelete", "yes");
				i.putExtra("buttonToWatch", 0);
				startActivity(i);
			}
		});
    }
}

