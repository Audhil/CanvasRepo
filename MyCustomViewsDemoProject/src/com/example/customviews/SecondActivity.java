package com.example.customviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.customcirclebattery3.R;
import com.example.customviews.view.CustomBottomTopFillCircleView;

//	links to check
//http://www.mathematische-basteleien.de/heart.htm

//http://stackoverflow.com/questions/24858531/filling-a-circle-gradually-from-bottom-to-top-android

//	superb tutorials
//http://android-er.blogspot.in/2014/05/draw-star-on-canvas.html
//http://android-er.blogspot.in/2014/05/draw-path-on-canvas-of-custom-view.html

public class SecondActivity extends Activity {

	private CustomBottomTopFillCircleView bottomTopCircularView;
	
	int value = 1;
	Handler myHandler = new Handler();
	
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			if(value <= 60){
				((TextView) findViewById(R.id.percentText)).setText(String.valueOf(value));
				bottomTopCircularView.setFillValue(value++);
				myHandler.postDelayed(this, 20);
			}
		}
	};

	@Override
	protected void onCreate(Bundle arg0) {	
		super.onCreate(arg0);
		setContentView(R.layout.activity_second);
		
		bottomTopCircularView = (CustomBottomTopFillCircleView)findViewById(R.id.circularView);		
		myHandler.postDelayed(runnable, 20);
	}
	
	@FromXml
	public void NavigateToThirdActivity(View view) {
		startActivity(new Intent(this,ThirdActivity.class));
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		myHandler.removeCallbacks(runnable);
	}
}