package com.example.turretshooter;

//import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;

public class MainActivity extends Activity {
	// private MediaPlayer Song;

	private MainView drawView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Song = MediaPlayer.create(MainActivity.this, R.raw.sandstorm);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		drawView = new MainView(this);
		setContentView(drawView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	protected void onResume() {
		super.onResume();
		// Song.start();
		// Song.setLooping(true);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Song.release();
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Song.start();
		// Song.setLooping(true);
	}
}
