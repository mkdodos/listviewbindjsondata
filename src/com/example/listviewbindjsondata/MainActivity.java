package com.example.listviewbindjsondata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

	
	
	ListView lvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayAdapter<CharSequence> adapterMain = ArrayAdapter
				.createFromResource(this, R.array.items,
						android.R.layout.simple_list_item_1);

		lvMain = (ListView) findViewById(R.id.lvMain);
		lvMain.setAdapter(adapterMain);
		lvMain.setOnItemClickListener(lvListener);

	}

	private OnItemClickListener lvListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			

			if (position == 0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, JSONActivity.class);
				startActivity(intent);
			}else{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, JSONHttpActivity.class);
				startActivity(intent);
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
