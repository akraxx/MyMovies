package fr.flst.mmargr.mymovies.main;

import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import fr.flst.mmargr.mymovies.R;
import fr.flst.mmargr.mymovies.AddMovieActivity;
import fr.flst.mmargr.mymovies.search.SearchMovieActivity;

public class MoviesListsActivity extends FragmentActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_lists);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_list_activity_menu, menu);
        return true;
    }
    
    @Override
   	public boolean onOptionsItemSelected(MenuItem item) {
   		switch (item.getItemId()) {
   		case R.id.menu_add:
   			Intent intentAdd = new Intent(MoviesListsActivity.this,
   					AddMovieActivity.class);
   			MoviesListsActivity.this.startActivity(intentAdd);
   			return true;
   		case R.id.menu_search:
   			Intent intentSearch = new Intent(MoviesListsActivity.this,
   					SearchMovieActivity.class);
   			MoviesListsActivity.this.startActivity(intentSearch);
   			return true;
   		default:
   			return super.onOptionsItemSelected(item);
   		}

   	}

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	Fragment fragment;
        	if(position == 0){
	            fragment = new ToWatchFragment();
        	}else{
        		fragment = new HistoryFragment();
        	}
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }

   
}
