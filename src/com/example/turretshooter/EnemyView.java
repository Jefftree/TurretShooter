package com.example.turretshooter;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

//Class is set to draw enemies on the screen
public class EnemyView extends View {

	// Vector to store individual enemy values as they appear on the screen
	public static Vector<EnemyValues> enemyVector;

	// Game values used to change values during gameplay
	public static GameValues gValues;

	// Metrics for display screen
	private DisplayMetrics metrics;

	// Used to generate random bitmap plus position according to grid layout
	private Random randNum;

	// Counter for interval of drawing enemies, speed for speed of enemies, base
	// for interval changes
	private int counter, speed, base;

	public EnemyView(Context context) {
		super(context);
		metrics = context.getResources().getDisplayMetrics();

		enemyVector = new Vector<EnemyValues>(1, 1);

		gValues = new GameValues();

		randNum = new Random();
		counter = 0;
		base = 100;
		speed = (int) Math.floor(metrics.heightPixels / 50);
	}

	// onDraw event for enemies
	public void onDraw(Canvas canvas) {

		if (counter == base) {
			drawNew();
			counter = 0;
		}
		counter += 1;

		for (int i = 0; i < enemyVector.size(); i++) {
			if (enemyVector.elementAt(i).getEnemyCap() <= 0) {
				int score = gValues.getScore()
						+ (enemyVector.elementAt(i).getEnemyType() * 10);
				gValues.setScore(score);
				removeEnemy(i);
			}
		}

		for (int i = 0; i < enemyVector.size(); i++) {
			canvas.save();
			int tempTime = (enemyVector.elementAt(i)).getTime() + 1;
			(enemyVector.elementAt(i)).setTime(tempTime);
			canvas.drawBitmap((enemyVector.elementAt(i)).getPanelEnemy(),
					(enemyVector.elementAt(i)).getPosX(),
					((enemyVector.elementAt(i)).getTime() * speed)
							- (enemyVector.elementAt(i)).getPanelEnemy()
									.getHeight(), null);
			(enemyVector.elementAt(i)).setEnemyRect(UpdateRectCoordinates(
					(enemyVector.elementAt(i)).getPosX(),
					(enemyVector.elementAt(i)).getTime() * speed,
					(enemyVector.elementAt(i)).getPanelEnemy()));
			canvas.restore();
		}
		invalidate();
	}

	// New enemy added to vector and enemy values are defined
	public void drawNew() {
		enemyVector.add(new EnemyValues());
		int iniElement = enemyVector.size() - 1;
		setValuesEnemy(enemyVector, iniElement);
	}

	public Rect UpdateRectCoordinates(int x, int y, Bitmap b) {
		Rect tempRect = new Rect(x, y - b.getHeight(), x + b.getWidth(), y);
		return tempRect;
	}

	public void setValuesEnemy(Vector<EnemyValues> eVector, int element) {
		Bitmap tempBitmap = Bitmap.createBitmap(
				(int) Math.floor(metrics.widthPixels / 19.933),
				(int) Math.floor(metrics.heightPixels / 6),
				Bitmap.Config.ARGB_8888);
		Canvas tempCanvas = new Canvas(tempBitmap);
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inScaled = true;
		opts.inSampleSize = 12;

		int numBitmap = randNum.nextInt(3) + 1;
		if (numBitmap == 1) {
			tempBitmap = BitmapFactory.decodeResource(getResources(),
					R.raw.motorcycle, opts);
			eVector.elementAt(element).setEnemyCap(2);
		} else if (numBitmap == 2) {
			tempBitmap = BitmapFactory.decodeResource(getResources(),
					R.raw.car, opts);
			eVector.elementAt(element).setEnemyCap(4);
		} else if (numBitmap == 3) {
			tempBitmap = BitmapFactory.decodeResource(getResources(),
					R.raw.truck, opts);
			eVector.elementAt(element).setEnemyCap(8);
		}

		eVector.elementAt(element).setEnemyType(numBitmap);
		eVector.elementAt(element).setPanelEnemy(tempBitmap);
		eVector.elementAt(element).setEnemy(tempCanvas);
		eVector.elementAt(element).setTime(0);
		eVector.elementAt(element).setPosX(PosX());
		eVector.elementAt(element).setEnemyRect(
				UpdateRectCoordinates(eVector.elementAt(element).getPosX(), 0,
						tempBitmap));
	}

	private void removeEnemy(int i) {
		enemyVector.remove(i);
	}

	// Top left position of enemy bitmap generated randomly
	public int PosX() {
		int X = (randNum.nextInt(7)) % 7;
		return (int) ((X * ((metrics.widthPixels - metrics.widthPixels / 6.102) / 7)) + Math
				.floor(metrics.widthPixels / 40));
	}

	public static Vector<EnemyValues> getEnemyVector() {
		return enemyVector;
	}

	public static GameValues getGValues() {
		return gValues;
	}
}
