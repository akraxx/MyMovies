package fr.flst.mmargr.mymovies;

import java.util.Calendar;

import fr.flst.mmargr.mymovies.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class ScheduleActivity extends Activity{
	private String title;
	private String year;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			setContentView(R.layout.schedule_activity);
			this.title = extras.getString("movieTitle");
			this.year = extras.getString("movieYear");
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_schedule, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_schedule:
            	DatePicker dp = (DatePicker)findViewById(R.id.datePicker);
            	TimePicker tp = (TimePicker)findViewById(R.id.timePicker);
            	int day = dp.getDayOfMonth();
            	int month = dp.getMonth();
            	int year = dp.getYear();
            	int hour = tp.getCurrentHour();
            	int minute = tp.getCurrentMinute();
            	
            	long startMillis = 0; 
            	long endMillis = 0;   
            	Calendar beginTime = Calendar.getInstance();
            	beginTime.set(year, month, day, hour, minute);
            	startMillis = beginTime.getTimeInMillis();
            	
            	Calendar endTime = Calendar.getInstance();
            	endTime.set(year, month, day, (hour+2)%12, minute);
            	endMillis = endTime.getTimeInMillis();
            	
            	Intent intent = new Intent(Intent.ACTION_EDIT);
            	intent.setType("vnd.android.cursor.item/event");
            	intent.putExtra("beginTime", startMillis);
            	intent.putExtra("allDay", true);
            	intent.putExtra("rrule", "FREQ=YEARLY");
            	intent.putExtra("endTime", endMillis);
            	intent.putExtra("title", "Watch "+ this.title + " from " + this.year);
            	startActivity(intent);
            	this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
