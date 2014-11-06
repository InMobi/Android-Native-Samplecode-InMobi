package com.inmobi.nativeadsample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.inmobi.nativeadsample.handleimpclick.NativeAdQueue;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NativeAdQueue.sharedQueue().initialize(this);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		InputStream inputStream = getResources().openRawResource(R.raw.response);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		 int i;
		 try {
		 i = inputStream.read();
		while (i != -1)
		  {
		   byteArrayOutputStream.write(i);
		   i = inputStream.read();
		   }
		    inputStream.close();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		final String js = byteArrayOutputStream.toString();
		final Map<String, String> map = new HashMap<String,String>();
		 map.put("key1", "value1");
		 map.put("key2", "value2");
		for(int j = 0 ; j< 10; j++) {
			final String ns = "im_5323_" + j;
			(new Thread() {
				public void run() {
					NativeAdQueue.recordImpression(ns, js, map);
				}
			}).start();
			//Log.v(InternalUtils.IM_TAG,"log" + i);
		}
	}

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

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
