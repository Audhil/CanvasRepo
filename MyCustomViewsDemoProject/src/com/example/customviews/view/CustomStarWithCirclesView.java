package com.example.customviews.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

//http://stackoverflow.com/questions/7007429/android-how-to-draw-triangle-star-square-heart-on-the-canvas
//http://codebybrian.com/2013/10/15/drawing_stars_android.html - done by this

public class CustomStarWithCirclesView extends View {

	private Path polyPath;
	private Paint innerStarPaint,outerStarPaint,rectPaint;
	private Bitmap bitmap;
	private Canvas tempCanvas;
	private Rect rect;
	private int rectLeft,rectRight,rectTop,rectBottom,rectWidthIs;
	
	public CustomStarWithCirclesView(Context context) {
		super(context);
		
		polyPath = new Path();
		
		innerStarPaint = new Paint();
		innerStarPaint.setDither(true);
		innerStarPaint.setColor(Color.parseColor("#33b5e5"));
		innerStarPaint.setStyle(Paint.Style.FILL);
			
		rect = new Rect();
		rectPaint = new Paint();
		rectPaint.setColor(Color.parseColor("#eeeeee"));
		
		outerStarPaint = new Paint();
		outerStarPaint.setDither(true);
		outerStarPaint.setColor(Color.LTGRAY);
		outerStarPaint.setStyle(Paint.Style.STROKE);
		outerStarPaint.setStrokeWidth(10);
	}

	public CustomStarWithCirclesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		polyPath = new Path();
		
		innerStarPaint = new Paint();
		innerStarPaint.setDither(true);
		innerStarPaint.setColor(Color.parseColor("#33b5e5"));
		innerStarPaint.setStyle(Paint.Style.FILL);
		
		rect = new Rect();
		rectPaint = new Paint();
		rectPaint.setColor(Color.parseColor("#eeeeee"));
		
		outerStarPaint = new Paint();
		outerStarPaint.setDither(true);
		outerStarPaint.setColor(Color.LTGRAY);
		outerStarPaint.setStyle(Paint.Style.STROKE);
		outerStarPaint.setStrokeWidth(10);
	}

	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		//	drawing innerStar using Canvas
		drawInnerFillStarInTempCanvas();
		canvas.drawBitmap(bitmap, 0,0, innerStarPaint);
		
		canvas.drawRect(rect, rectPaint);

		//	drawing outerStar using Canvas
		drawOuterStrokeStarInTempCanvas();
		canvas.drawBitmap(bitmap, 0,0, outerStarPaint);
	}
	
	//	inner Star
	private void drawInnerFillStarInTempCanvas() {		
		
		bitmap = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
		tempCanvas = new Canvas(bitmap);
		
		int measuredWidth = getWidth();
		int measuredHeight = getHeight();

		int x = (measuredWidth / 2);
		int y = (measuredHeight / 2);
		int outerRadius = measuredWidth / 4;
		int innerRadius = outerRadius / 3;
		
		int points = 5, fillPercent = 1;

		if (points < 3)
			return;

		float a = (float) (Math.PI * 2) / (points * 2);
		int workingRadius = outerRadius;
		polyPath.reset();

		tempCanvas.translate(x, y);
		for (int j = 0; j < ((fillPercent < 1) ? 2 : 1); j++) {
			polyPath.moveTo(workingRadius, 0);
			for (int i = 1; i < points * 2; i++) {
				workingRadius = (workingRadius == outerRadius) ? innerRadius : outerRadius;
				float xPt = (float) (workingRadius * Math.cos(a * i));
				float yPt = (float) (workingRadius * Math.sin(a * i));
				polyPath.lineTo(xPt, yPt);
			}
			polyPath.close();
			outerRadius -= outerRadius * fillPercent;
			innerRadius = outerRadius / 2;
			a = -a;
		}

		float startAngle = 126;

		tempCanvas.rotate(startAngle);
		tempCanvas.drawPath(polyPath, innerStarPaint);		
	}

	//	set all sides of rect
	private void formHidingRectAroundStar(int screenWidth, int screenHeight, int outerRadius) {		
		rectLeft = screenWidth - (screenWidth / 2 + outerRadius - 25);
		rectRight = (screenWidth / 2 + outerRadius) - 25;
		rectTop = screenHeight - (screenHeight / 2 + outerRadius);
		rectBottom = screenHeight / 2 + outerRadius;
		
		rectWidthIs = rectRight - rectLeft;
		
		rect.set(rectLeft, rectTop, rectRight, rectBottom);
	}

	//	outerStar with only Stroke
	private void drawOuterStrokeStarInTempCanvas() {
		bitmap = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
		tempCanvas = new Canvas(bitmap);
		
		int measuredWidth = getWidth();
		int measuredHeight = getHeight();

		int x = (measuredWidth / 2);
		int y = (measuredHeight / 2);
		int outerRadius = measuredWidth / 4;
		int innerRadius = outerRadius / 3;

		int points = 5, fillPercent = 1;

		if (points < 3)
			return;

		float a = (float) (Math.PI * 2) / (points * 2);
		int workingRadius = outerRadius;
		polyPath.reset();

		tempCanvas.translate(x, y);
		for (int j = 0; j < ((fillPercent < 1) ? 2 : 1); j++) {
			polyPath.moveTo(workingRadius, 0);
			for (int i = 1; i < points * 2; i++) {
				workingRadius = (workingRadius == outerRadius) ? innerRadius : outerRadius;
				float xPt = (float) (workingRadius * Math.cos(a * i));
				float yPt = (float) (workingRadius * Math.sin(a * i));
				polyPath.lineTo(xPt, yPt);
			}
			polyPath.close();
			outerRadius -= outerRadius * fillPercent;
			innerRadius = outerRadius / 2;
			a = -a;
		}

		float startAngle = 126;

		tempCanvas.rotate(startAngle);
		tempCanvas.drawPath(polyPath, outerStarPaint);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		//	where as getWidth() / 4 is also outer radius of Outer circle of Star
		formHidingRectAroundStar(getWidth(),getHeight(),getWidth() / 4);
	}
	
	//	from activity
	public void setRectLeftSideValue() {		
		int factor = rectWidthIs / 50; 
		rectLeft += factor;
		rect.set(rectLeft, rectTop, rectRight, rectBottom);
	}
}