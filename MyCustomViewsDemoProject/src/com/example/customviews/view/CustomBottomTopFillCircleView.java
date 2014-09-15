package com.example.customviews.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

//	tuto @ http://stackoverflow.com/questions/24858531/filling-a-circle-gradually-from-bottom-to-top-android

public class CustomBottomTopFillCircleView extends View {

	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 100;
	
	PointF centerPoint = new PointF();
	private int value,radius;
	
	private Paint mPaint,mFillPaint;
	private RectF circleRect;
	private Path mPath;

	public CustomBottomTopFillCircleView(Context context) {
		super(context);
		mPaint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG);
		circleRect = new RectF();
		mPath = new Path();
		mFillPaint = new Paint();
	}

	public CustomBottomTopFillCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG);
		circleRect = new RectF();
		mPath = new Path();
		mFillPaint = new Paint();
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		centerPoint.x = getWidth() / 2;
		centerPoint.y = getHeight() / 2;
		
		radius = Math.min(getWidth(), getHeight()) / 2;
		circleRect.set(centerPoint.x - radius, centerPoint.y - radius, centerPoint.x + radius, centerPoint.y + radius);
		
		setPaths();
	}
	
	//	values to be sent from activity
	public void setFillValue(int value) {
		adjustValues(value);
		setPaths();
		invalidate();
	}

	private void setPaths() {
		
		float y = centerPoint.y + radius - (2 * radius * value / 100 - 1);
        float x = centerPoint.x - (float) Math.sqrt(Math.pow(radius, 2) - Math.pow(y - centerPoint.y, 2));

        float angle = (float) Math.toDegrees(Math.atan((centerPoint.y - y) / (x - centerPoint.x)));
        float startAngle = 180 - angle;
        float sweepAngle = 2 * angle - 180;

        mPath.rewind();
        mPath.addArc(circleRect, startAngle, sweepAngle);
        mPath.close();
	}

	private void adjustValues(int value) {
		this.value = Math.min(MAX_VALUE, Math.max(MIN_VALUE, value));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		mPaint.setDither(true);
		mPaint.setColor(Color.LTGRAY);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(15);
		
		mFillPaint.setColor(Color.parseColor("#33b5e5"));
		mFillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mFillPaint.setStrokeWidth(5);
		
		canvas.drawPath(mPath,mFillPaint);
		canvas.drawCircle(centerPoint.x, centerPoint.y, radius, mPaint);
	}
}