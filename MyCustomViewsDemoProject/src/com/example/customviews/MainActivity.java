package com.example.customviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.customcirclebattery3.R;
import com.example.customviews.view.CustomCircularRingView;

//	some links for reference
//http://android-er.blogspot.in/2011/08/canvasdrawarc.html
//http://stackoverflow.com/questions/3874424/android-looking-for-a-drawarc-method-with-inner-outer-radius
//https://www.google.co.in/search?q=drawarc+android+example&rlz=1C5CHFA_enIN584IN584&oq=dra&aqs=chrome.0.69i59j69i60j69i65j69i60j69i57j69i60.1439j0j7&sourceid=chrome&es_sm=119&ie=UTF-8
//http://www.techrepublic.com/blog/software-engineer/bouncing-a-ball-on-androids-canvas/
//http://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-touch-interaction--mobile-19202
//https://www.google.co.in/webhp?sourceid=chrome-instant&rlz=1C5CHFA_enIN584IN584&ion=1&espv=2&ie=UTF-8#q=canvas%20tutorial%20android
//https://www.google.co.in/webhp?sourceid=chrome-instant&rlz=1C5CHFA_enIN584IN584&ion=1&espv=2&ie=UTF-8#q=canvas%20tutorial%20android
//http://stackoverflow.com/questions/11131954/how-to-draw-arc-between-two-points-on-the-canvas
//https://www.google.co.in/webhp?sourceid=chrome-instant&rlz=1C5CHFA_enIN584IN584&ion=1&espv=2&ie=UTF-8#q=drawarc%20android%20example
//								
//http://android.okhelp.cz/timer-task-timertask-run-cancel-android-example/
//http://android.okhelp.cz/asynctask-example-android-with-progressbar/
//http://android.okhelp.cz/timer-simple-timertask-java-android-example/
//http://stackoverflow.com/questions/15558673/android-draw-circle-with-path
//												
//http://stackoverflow.com/questions/20697189/fill-color-on-bitmap-in-android?noredirect=1#comment30999957_20697189
//http://stackoverflow.com/questions/14988701/battery-circle-like-battery-widget-reborn
//https://www.google.co.in/webhp?sourceid=chrome-instant&rlz=1C5CHFA_enIN584IN584&ion=1&espv=2&ie=UTF-8#q=to%20draw%20circle%20based%20on%20percentage%20in%20android
//															
//http://android-er.blogspot.in/2011/08/example-of-drawing-on-view.html
//http://www.java2s.com/Code/Android/2D-Graphics/Drawacircle.htm
//http://www.techiecommunity.net/Android/Android-Canvas-Draw-Example
//																		
//http://www.compiletimeerror.com/2013/09/introduction-to-2d-drawing-in-android.html#.VBE73y6Sxy9

public class MainActivity extends Activity {

	protected static final String TAG = MainActivity.class.getSimpleName();

	private Handler handler;
	private CustomCircularRingView myView;
	int percent = 0;

	Runnable runnable = new Runnable() {

		@Override
		public void run() {

			if (percent <= 80) {
				((TextView) findViewById(R.id.percentText)).setText(String.valueOf(percent));
				myView.changePercentage(percent++);
				myView.invalidate();				
				handler.postDelayed(this, 10);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		handler = new Handler();
		myView = (CustomCircularRingView) findViewById(R.id.customCircularRingView);

		handler.postDelayed(runnable, 10);
	}
	
	@FromXml
	public void NavigateToSecondActivity(View view) {
		startActivity(new Intent(this,SecondActivity.class));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		handler.removeCallbacks(runnable);
	}
}