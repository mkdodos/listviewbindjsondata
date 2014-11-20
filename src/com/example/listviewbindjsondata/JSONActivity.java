package com.example.listviewbindjsondata;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class JSONActivity extends ActionBarActivity {

	ListView lv;
	String result = "";
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);
		
		
		try {
			InputStream is = getAssets().open("works.txt");
			tv = (TextView) findViewById(R.id.tvHello);

			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			result = new String(buffer);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject c;
		ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
		try {
			JSONObject obj = new JSONObject(result);
			JSONArray array = obj.getJSONArray("works");
			String s = "";
			for (int i = 0; i < array.length(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				c = array.getJSONObject(i);
				map.put("size1", "Φ" + c.getString("size1"));
				map.put("work_id", c.getString("work_id"));
				arraylist.add(map);
			}
			ListAdapter adapter = new SimpleAdapter(JSONActivity.this,
					arraylist, R.layout.list_item, new String[] { "work_id",
							"size1" }, new int[] { R.id.textView2,
							R.id.textView1 });
			lv = (ListView) findViewById(R.id.lvIdEdu);
			 lv.setAdapter(adapter);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 取得陣列物件
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.json, menu);
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
