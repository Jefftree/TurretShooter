package com.example.turretshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

//Class is for startup screen
public class StartupView extends View {

	// Display metrics for startup screen
	private DisplayMetrics metrics;

	// Paint to draw text
	private Paint paint;

	private boolean startGame;

	public StartupView(Context context) {
		super(context);
		metrics = context.getResources().getDisplayMetrics();
		paint = new Paint();

		startGame = false;
	}

	public boolean onTouchEvent(MotionEvent e) {
		int k = e.getAction();

		if (k == MotionEvent.ACTION_DOWN) {
			startGame = true;
		}

		invalidate();
		return true;
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(Color.BLACK);

		canvas.drawRect(0, 0, metrics.widthPixels, metrics.heightPixels, paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize((int) (metrics.widthPixels / 32));
		canvas.drawText("TAP ANYWHERE TO BEGIN",
				(int) (metrics.widthPixels / 3), metrics.heightPixels / 2,
				paint);

		if (EnemyView.getGValues().getDamage() <= 0) {
			startGame = false;
		}

		invalidate();
	}

	// Returns true if onTouchEvent occurs
	public boolean getStartGame() {
		return startGame;
	}

	// Sets startgame to boolean value
	public void setStartGame(boolean sG) {
		startGame = sG;
	}
}
