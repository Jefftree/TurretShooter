package com.example.turretshooter;

import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

// Class is set to draw  bullets onto screen
public class BulletView extends View {

	// Vector to store individual bullet values as they appear on the screen
	private static Vector<BulletValues> bulletVector;

	// Speed factor for bullets
	private final int speed;

	// Defines the initial center of any top and bottom bullet
	private final int iniBulletX, iniBulletY, iniBulletX2;

	// Metrics for display screen
	private DisplayMetrics metrics;

	// Paint used to color in different canvases
	private Paint paint;

	// Used to get x and y values on touch event
	private int curX, curY;

	public BulletView(Context context) {
		super(context);
		metrics = context.getResources().getDisplayMetrics();
		paint = new Paint();

		bulletVector = new Vector<BulletValues>(2, 2);

		speed = (int) Math.floor(metrics.heightPixels / 12);

		iniBulletX = (metrics.widthPixels / 2)
				- ((int) (Math.floor((metrics.widthPixels / 2) / 1.05097) + 1 + Math
						.floor((metrics.widthPixels / 2) / 66.4444)));
		iniBulletY = (metrics.heightPixels - metrics.heightPixels / 9)
				- (int) (Math
						.floor((metrics.heightPixels - metrics.heightPixels / 9) / 1.1015) + Math
						.floor(metrics.heightPixels / 80));
		iniBulletX2 = (int) (Math.floor((metrics.widthPixels / 2) / 1.05097) + 1 + Math
				.floor((metrics.widthPixels / 2) / 66.4444))
				- (metrics.widthPixels / 2);
	}

	// Touch event for bullets
	public boolean onTouchEvent(MotionEvent e) {
		int k = e.getAction();

		if (k == MotionEvent.ACTION_DOWN) {
			curX = (int) e.getX();
			curY = (int) e.getY();

			// Two new bullets are added to vector once touch event occurs
			bulletVector.add(new BulletValues());
			bulletVector.add(new BulletValues());

			// Element number of vector that holds the bullets that were just
			// added to vector
			int iniElement = bulletVector.size() - 2;

			// Method call to set all bullet values according class BulletValues
			setValuesBullet(bulletVector, iniElement);
		}

		invalidate();
		return true;
	}

	// Draw Bullets according to specified code
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Checks if any bullet is out of bounds or has collided and then calls
		// remove bullet method
		for (int i = 0; i < bulletVector.size(); i++) {
			if ((bulletVector.elementAt(i)).getBulletRect().bottom <= 0
					|| (bulletVector.elementAt(i)).getBulletRect().right <= 0
					|| (bulletVector.elementAt(i)).getBulletRect().left >= (metrics.widthPixels - metrics.widthPixels / 6.102)
					|| (bulletVector.elementAt(i)).getBulletRect().top >= metrics.heightPixels
					|| (bulletVector.elementAt(i)).getBulletCap() == 0) {
				removeBullet(i);
			}
		}

		// Draw Bullets
		for (int i = 0; i < bulletVector.size(); i++) {

			(bulletVector.elementAt(i)).getBullet().drawARGB(0, 0, 0, 0);
			drawBullet(i);
			canvas.save();

			// Rotation of canvas according to the values set for bullet
			canvas.rotate((bulletVector.elementAt(i)).getRotation(),
					(bulletVector.elementAt(i)).getCenterLocX(),
					(bulletVector.elementAt(i)).getCenterLocY());

			if ((bulletVector.elementAt(i)).getIsTopBullet() == true) {

				// Actual animation of bullet as time increase for top bullet
				int tempTime = (bulletVector.elementAt(i)).getTime() + 1;
				(bulletVector.elementAt(i)).setTime(tempTime);

				canvas.drawBitmap(
						(bulletVector.elementAt(i)).getPanelBullet(),
						(float) (((bulletVector.elementAt(i)).getCenterLocX() - iniBulletX) - Math
								.floor((metrics.widthPixels / 2) / 66.4444)),
						(float) Math
								.floor((metrics.heightPixels - metrics.heightPixels / 9) / 1.1015)
								- (bulletVector.elementAt(i)).getTime() * speed,
						null);

				// Rectangle meant for bullet collision is updated as bullet is
				// moving
				(bulletVector.elementAt(i))
						.setBulletRect(updateRectCoordinates(
								bulletlocX(
										iniBulletX,
										iniBulletY
												+ ((bulletVector.elementAt(i))
														.getTime() * speed),
										(bulletVector.elementAt(i))
												.getRotation() * (-1),
										(bulletVector.elementAt(i))
												.getCenterLocX()),
								bulletlocY(
										iniBulletX,
										iniBulletY
												+ ((bulletVector.elementAt(i))
														.getTime() * speed),
										(bulletVector.elementAt(i))
												.getRotation() * (-1),
										(bulletVector.elementAt(i))
												.getCenterLocY())));

			} else if ((bulletVector.elementAt(i)).getIsTopBullet() == false) {

				// Actual animation of bullet as time increase for bottom bullet
				int tempTime2 = (bulletVector.elementAt(i)).getTime2() + 1;
				(bulletVector.elementAt(i)).setTime2(tempTime2);

				canvas.drawBitmap(
						(bulletVector.elementAt(i)).getPanelBullet(),
						(float) (((bulletVector.elementAt(i)).getCenterLocX() - iniBulletX2) - Math
								.floor((metrics.widthPixels / 2) / 66.4444)),
						(float) Math
								.floor((metrics.heightPixels - metrics.heightPixels / 9) / 1.1015)
								- (bulletVector.elementAt(i)).getTime2()
								* speed, null);

				// Rectangle meant for bullet collision is updated as bullet is
				// moving
				(bulletVector.elementAt(i))
						.setBulletRect(updateRectCoordinates(
								bulletlocX(
										iniBulletX2,
										iniBulletY
												+ ((bulletVector.elementAt(i))
														.getTime2() * speed),
										(bulletVector.elementAt(i))
												.getRotation() * (-1),
										(bulletVector.elementAt(i))
												.getCenterLocX()),
								bulletlocY(
										iniBulletX2,
										iniBulletY
												+ ((bulletVector.elementAt(i))
														.getTime2() * speed),
										(bulletVector.elementAt(i))
												.getRotation() * (-1),
										(bulletVector.elementAt(i))
												.getCenterLocY())));
			}

			canvas.restore();
		}
		invalidate();
	}

	// Method to draw bullet
	private void drawBullet(int i) {
		paint.setARGB(255, 255, 0, 0);
		paint.setStyle(Style.FILL);

		(bulletVector.elementAt(i)).getBullet().drawCircle(
				(float) Math.floor(metrics.heightPixels / 80),
				(float) Math.floor(metrics.heightPixels / 80),
				(float) Math.floor(metrics.heightPixels / 80), paint);
	}

	// Method to remove bullet from vector
	private void removeBullet(int i) {
		bulletVector.remove(i);
	}

	// Method updates bullet rotation when on touch event happens
	private int updateRotation(int x, int y, int centerX, int centerY) {
		double rad = Math.atan2((double) centerX - x, (double) centerY - y);
		return (int) Math.toDegrees(rad) * (-1);
	}

	// Method updates Rect coordinates for on collision purposes
	private Rect updateRectCoordinates(int x, int y) {
		Rect tempRect = new Rect(x
				- (int) Math.floor(metrics.heightPixels / 80), y
				- (int) Math.floor(metrics.heightPixels / 80),
				(int) Math.floor(metrics.heightPixels / 80) + x,
				(int) Math.floor(metrics.heightPixels / 80) + y);
		return tempRect;
	}

	// Physical X location of bullet as it is moving
	private int bulletlocX(int x, int y, int rotation, int centerX) {
		return (int) (centerX - ((y)
				* Math.sin(Math.toRadians((double) rotation)) + (x)
				* Math.sin(Math.toRadians((double) 90 - rotation))));
	}

	// Physical Y location of bullet as it is moving
	private int bulletlocY(int x, int y, int rotation, int centerY) {
		return (int) ((centerY - (y)
				* Math.cos(Math.toRadians((double) rotation))) + (x)
				* Math.cos(Math.toRadians((double) 90 - rotation)));
	}

	// Method used to assign values to new empty bullets instantiated at on
	// touch event
	public void setValuesBullet(Vector<BulletValues> bVector, int element) {
		Bitmap tempBitmap = Bitmap.createBitmap(
				(int) (metrics.widthPixels / 40),
				(int) metrics.widthPixels / 40, Bitmap.Config.ARGB_8888);
		Canvas tempCanvas = new Canvas(tempBitmap);

		bVector.elementAt(element).setPanelBullet(tempBitmap);
		bVector.elementAt(element + 1).setPanelBullet(tempBitmap);
		bVector.elementAt(element).setBullet(tempCanvas);
		bVector.elementAt(element + 1).setBullet(tempCanvas);
		bVector.elementAt(element)
				.setCenterLocX(
						(int) (((metrics.widthPixels - metrics.widthPixels / 6.102) / 2)));
		bVector.elementAt(element + 1)
				.setCenterLocX(
						(int) (((metrics.widthPixels - metrics.widthPixels / 6.102) / 2)));
		bVector.elementAt(element).setCenterLocY(
				metrics.heightPixels - metrics.heightPixels / 9);
		bVector.elementAt(element + 1).setCenterLocY(
				metrics.heightPixels - metrics.heightPixels / 9);
		bVector.elementAt(element).setRotation(
				updateRotation(curX, curY, bVector.elementAt(element)
						.getCenterLocX(), bVector.elementAt(element)
						.getCenterLocY()));
		bVector.elementAt(element + 1).setRotation(
				updateRotation(curX, curY, bVector.elementAt(element + 1)
						.getCenterLocX(), bVector.elementAt(element + 1)
						.getCenterLocY()));
		bVector.elementAt(element)
				.setBulletRect(
						updateRectCoordinates(
								bulletlocX(iniBulletX, iniBulletY, bVector
										.elementAt(element).getRotation()
										* (-1), bVector.elementAt(element)
										.getCenterLocX()),
								bulletlocY(iniBulletX, iniBulletY, bVector
										.elementAt(element).getRotation()
										* (-1), bVector.elementAt(element)
										.getCenterLocY())));
		bVector.elementAt(element + 1).setBulletRect(
				updateRectCoordinates(
						bulletlocX(iniBulletX2, iniBulletY,
								bVector.elementAt(element + 1).getRotation()
										* (-1), bVector.elementAt(element + 1)
										.getCenterLocX()),
						bulletlocY(iniBulletX2, iniBulletY,
								bVector.elementAt(element + 1).getRotation()
										* (-1), bVector.elementAt(element + 1)
										.getCenterLocY())));
		bVector.elementAt(element).setTime(0);
		bVector.elementAt(element).setTime2(0);
		bVector.elementAt(element + 1).setTime(0);
		bVector.elementAt(element + 1).setTime2(0);
		bVector.elementAt(element).setIsTopBullet(true);
		bVector.elementAt(element + 1).setIsTopBullet(false);
		bVector.elementAt(element).setBulletCap(1);
		bVector.elementAt(element + 1).setBulletCap(1);
	}

	// Returns bullet vector when called upon
	public static Vector<BulletValues> getBulletVector() {
		return bulletVector;
	}
}