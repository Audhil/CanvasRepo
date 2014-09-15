package com.example.customviews.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

//http://stackoverflow.com/questions/8441375/how-to-find-pixel-color-in-custom-view
//http://stackoverflow.com/questions/5916216/how-to-change-the-color-of-certain-pixels-in-bitmap-android
//http://stackoverflow.com/questions/4013725/converting-a-canvas-into-bitmap-image-in-android
//http://stackoverflow.com/questions/5916216/how-to-change-the-color-of-certain-pixels-in-bitmap-android

public class CustomView extends View {

	private Bitmap bitmap;
	private Canvas bitmapTempCanvas;
	private Paint bitmapPaint;
	private int[] pixelsIntArray;

	public CustomView(Context context) {
		super(context);
		
		bitmapPaint = new Paint();
		bitmapPaint.setColor(Color.BLACK);
		bitmapPaint.setStyle(Paint.Style.FILL);
	}
	
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		bitmapPaint = new Paint();
		bitmapPaint.setColor(Color.BLACK);
		bitmapPaint.setStyle(Paint.Style.FILL);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(bitmap == null){
			bitmap = Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(),Bitmap.Config.ARGB_8888);
			bitmapTempCanvas = new Canvas(bitmap);
			pixelsIntArray = new int[bitmap.getWidth() * bitmap.getHeight()];
		}
		
		if(bitmapTempCanvas != null){
			bitmapTempCanvas.drawCircle(100, 100, 100, bitmapPaint);
			
			//	getting all Pixels into a int array
			bitmap.getPixels(pixelsIntArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
			
			//	finding required pixels and changing its color
//			for (int i = 0; i < pixelsIntArray.length; i++) {
//				will iterate to all pixels and hence will change color whole at once
//			}
			
//			for (int i = 0; i < bitmap.getWidth() * bitmap.getHeight(); i++) {
//				will iterate to all pixels and hence will change color whole at once
//			}
			//	for only first x rows
			for (int i = 0; i < bitmap.getWidth() * 30; i++) {
				if(pixelsIntArray[i] == Color.BLACK){
					pixelsIntArray[i] = Color.RED;
				}
			}
			bitmap.setPixels(pixelsIntArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(),bitmap.getHeight());
		}
		
		if(bitmap != null){
			canvas.drawBitmap(bitmap, 0, 0, null);
		}
	}
}