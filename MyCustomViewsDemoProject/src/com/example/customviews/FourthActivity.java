package com.example.customviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.customcirclebattery3.R;

//http://android-er.blogspot.in/2011/08/drawpath-on-canvas.html

public class FourthActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four);
	}
	
	@FromXml
	public void NavigateToFifthActivity(View view) {
		startActivity(new Intent(this,FifthActivity.class));
	}
}