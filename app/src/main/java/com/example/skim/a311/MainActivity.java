package com.example.skim.a311;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements Map.OnFragmentInteractionListener, Feed.OnFragmentInteractionListener,Report.OnFragmentInteractionListener,Contact.OnFragmentInteractionListener {

	private Toolbar toolbar;

	@Override


	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("safeHOU");
		setContentView(R.layout.activity_main);


		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		Fragment fragment = null;
		Class Home = Map.class;

		try {
			fragment = (Fragment) Home.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		FragmentManager fragmentManager = getSupportFragmentManager();

		fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
		BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
		bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelected(@IdRes int tabId) {
				Fragment fragment = null;
				Class fragmentClass;
				switch(tabId){
					case R.id.tab_map:
						fragmentClass = Map.class;
						break;


					case R.id.tab_crimefeed:
						fragmentClass = Feed.class;
						break;
					case R.id.tab_report:
						fragmentClass = Report.class;
						break;
					case R.id.tab_references:
						fragmentClass = Contact.class;
						break;


					default:
						fragmentClass = Map.class;
				}
				try {
					fragment = (Fragment) fragmentClass.newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}

				FragmentManager fragmentManager = getSupportFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

			}
		});
	}
	public void onFragmentInteraction(Uri uri) {
		//you can leave it empty
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

                /*
                Intent in = new Intent(this,Settings.class);
                startActivity(in);
                return true;
                */
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		return true;
	}
}
