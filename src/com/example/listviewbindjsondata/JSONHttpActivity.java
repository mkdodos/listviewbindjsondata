package com.example.listviewbindjsondata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class JSONHttpActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);

		new LoadData().execute();

	}

	class LoadData extends AsyncTask<Void, Void, Void> {

		ListView lv;
		String result = "";

		@Override
		protected Void doInBackground(Void... params) {

			InputStream is = null;

			// 使用http post的方式
			try {
				String url = "http://192.168.0.104/android_connect/get_golds.php";
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity httpEntity = response.getEntity();
				is = httpEntity.getContent();// 取得InputStream
			} catch (Exception e) {
				Log.e("log_tag", "Error in http connection " + e.toString());
			}

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);

				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				result = sb.toString();
				is.close();

			} catch (IOException e) {

				Log.e("Ch08GetIntSto", e.toString());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void args) {

			JSONObject c;
			ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
			try {
				JSONObject obj = new JSONObject(result);
				JSONArray array = obj.getJSONArray("rows");
				String s = "";
				for (int i = 0; i < array.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					c = array.getJSONObject(i);
					map.put("gdate",  c.getString("gdate"));
					map.put("price", "$"+c.getString("price"));
					arraylist.add(map);
				}
				ListAdapter adapter = new SimpleAdapter(JSONHttpActivity.this,
						arraylist, R.layout.list_item, new String[] {
								"gdate", "price" }, new int[] {
								R.id.textView2, R.id.textView1 });
				lv = (ListView) findViewById(R.id.lvIdEdu);
				lv.setAdapter(adapter);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 取得陣列物件

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jsonhttp, menu);
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
