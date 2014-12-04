package com.example.turretshooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class TurretView extends View {

	/*
	 * CANVAS COMPONENTS
	 */

	// Bitmap for turret
	private Bitmap panelTurret;

	// Canvas for turret
	private Canvas turret;

	// Turret rectangle for collision purposes
	private Rect turretRect1, turretRect2;

	// For turret rotation angle
	private int rotation;

	/*
	 * OTHER COMPONENTS
	 */

	// Metrics for display screen
	private DisplayMetrics metrics;

	// Paint used to color in different canvases
	private Paint paint;

	/*
	 * PRIMARY DATA TYPES
	 */

	// Used to get x and y values on touch event
	private int curX, curY;

	// Defines the center of the turret
	private int centerLocX, centerLocY;

	public TurretView(Context context) {
		super(context);
		metrics = context.getResources().getDisplayMetrics();
		paint = new Paint();

		panelTurret = Bitmap.createBitmap(metrics.widthPixels,
				metrics.heightPixels, Bitmap.Config.ARGB_8888);
		turret = new Canvas(panelTurret);

		centerLocX = (int) ((metrics.widthPixels - metrics.widthPixels / 6.102) / 2);
		centerLocY = (int) metrics.heightPixels - metrics.heightPixels / 9;

		turretRect1 = new Rect(
				(int) (centerLocX - Math.floor(metrics.heightPixels / 14.4)),
				(int) (centerLocY - Math.floor(metrics.heightPixels / 14.4)),
				(int) (centerLocX + Math.floor(metrics.heightPixels / 14.4)),
				(int) (centerLocY + Math.floor(metrics.heightPixels)));

		turretRect2 = new Rect(0,
				(int) (metrics.heightPixels - metrics.heightPixels / 9),
				(int) metrics.widthPixels, (int) metrics.heightPixels);
		rotation = 0;
	}

	// On touch event to update turret rotation
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		int k = e.getAction();

		if (k == MotionEvent.ACTION_DOWN) {
			curX = (int) e.getX();
			curY = (int) e.getY();
			updateRotation(curX, curY);
		}

		invalidate();
		return true;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Draw Turret
		turret.drawARGB(0, 0, 0, 0);
		drawTurret();
		canvas.save();
		canvas.rotate(rotation, centerLocX, centerLocY);
		canvas.drawBitmap(panelTurret, 0, 0, null);
		canvas.restore();

		invalidate();
	}

	private void drawTurret() {

		paint.setARGB(255, 0, 0, 0);
		paint.setStyle(Style.FILL);
		turret.drawCircle((float) (centerLocX), centerLocY,
				(float) Math.floor(metrics.heightPixels / 18), paint);
		turret.drawRect((float) ((centerLocX) - (centerLocX - Math
				.floor(centerLocX / 1.0528))), (float) centerLocY,
				(float) ((centerLocX) - (centerLocX - Math
						.floor(centerLocX / 1.017))), (float) Math
						.floor(centerLocY / 1.1034), paint);
		turret.drawRect((float) ((centerLocX) - (centerLocX - Math
				.floor(centerLocX / 0.9522))), (float) centerLocY,
				(float) (float) ((centerLocX) - (centerLocX - (Math
						.floor(centerLocX / 0.9836) + 1))), (float) Math
						.floor(centerLocY / 1.1034), paint);
	}

	private void updateRotation(int x, int y) {
		double rad = Math.atan2((double) (centerLocX) - x, (double) centerLocY
				- y);
		rotation = (int) Math.toDegrees(rad) * (-1);
	}

	// Return Rect for wall
	public Rect getWallRect() {
		return turretRect2;
	}

	// Return Rect for turret expansion
	public Rect getTurretRect() {
		return turretRect1;
	}
}
