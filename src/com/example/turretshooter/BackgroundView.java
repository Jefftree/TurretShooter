package com.example.turretshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.View;

// Class is set for the background view of the game
public class BackgroundView extends View {

	// Bitmap and canvas for back panel
	private Bitmap backPanel;
	private Canvas background;

	// Metrics of device being used
	private DisplayMetrics metrics;

	// Paint variable for background design
	private Paint paint;

	public BackgroundView(Context context) {
		super(context);
		metrics = context.getResources().getDisplayMetrics();
		backPanel = Bitmap.createBitmap(metrics.widthPixels,
				metrics.heightPixels, Bitmap.Config.ARGB_8888);
		background = new Canvas(backPanel);
		paint = new Paint();
	}

	// Draw Background according to specified code
	@Override
	public void onDraw(Canvas canvas) {
		background.drawARGB(255, 26, 117, 47);
		int gridInterval = (int) ((metrics.widthPixels - metrics.widthPixels / 6.102) / 7);
		paint.setARGB(100, 255, 255, 255);
		paint.setStyle(Style.FILL_AND_STROKE);
		for (int i = 1; i < 7; i++) {
			background.drawLine(gridInterval * i, 0, gridInterval * i,
					metrics.heightPixels, paint);
		}
		for (int i = 1; i < 6; i++) {
			background.drawLine(0, gridInterval * i, metrics.widthPixels,
					gridInterval * i, paint);
		}
		paint.setARGB(255, 240, 230, 140);
		paint.setStyle(Style.FILL_AND_STROKE);
		background.drawRect(0, metrics.heightPixels - metrics.heightPixels / 9,
				metrics.widthPixels, metrics.heightPixels, paint);
		background
				.drawCircle(
						(float) ((metrics.widthPixels - metrics.widthPixels / 6.102) / 2),
						(float) (metrics.heightPixels - metrics.heightPixels / 9),
						(float) (Math.floor(metrics.heightPixels / 14.4)),
						paint);
		canvas.drawBitmap(backPanel, 0, 0, null);
		invalidate();
	}
}