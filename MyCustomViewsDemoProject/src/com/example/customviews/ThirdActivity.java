package com.example.customviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.customcirclebattery3.R;
import com.example.customviews.view.CustomStarWithCirclesView;

//	very important : http://colintmiller.com/how-to-add-text-over-a-progress-bar-on-android/

public class ThirdActivity extends Activity {

	private CustomStarWithCirclesView ratingStar;
	
	Handler myHandler = new Handler();
	
	int value = -3;
	Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			if(value <= 50){
				
				if(value < 10){
					((TextView)findViewById(R.id.RatingTxtView)).setText("0."+value);
				}
				else{
					((TextView)findViewById(R.id.RatingTxtView)).setText(String.valueOf(value / 10)+"."+String.valueOf(value % 10));
				}
				
				ratingStar.setRectLeftSideValue();
				value++;
				ratingStar.invalidate();
				myHandler.postDelayed(this, 3);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);
		
		ratingStar = (CustomStarWithCirclesView)findViewById(R.id.customRatingStarView);
		
		myHandler.postDelayed(runnable, 3);
	}
	
	@FromXml
	public void NavigateToFourthActivity(View view) {
		startActivity(new Intent(this,FourthActivity.class));
	}
}