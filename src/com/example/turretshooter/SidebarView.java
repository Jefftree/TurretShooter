package com.example.turretshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

//Class to draw sidebar
public class SidebarView extends View { // Class for sidebar

	// metrics for the sidebar
	private DisplayMetrics metrics;

	// paint for text drawn
	private Paint paint;

	MainActivity activity;

	public SidebarView(Context context) {
		super(context);
		metrics = context.getResources().getDisplayMetrics();
		paint = new Paint();
		activity = new MainActivity();

	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(Color.BLACK);

		canvas.drawRect(
				(int) (metrics.widthPixels - (metrics.widthPixels / 6.102)), 0,
				(int) (metrics.widthPixels), metrics.heightPixels, paint);

		paint.setColor(Color.RED);
		canvas.drawLine(
				(int) (metrics.widthPixels - (metrics.widthPixels / 6.102)), 0,
				(int) (metrics.widthPixels - (metrics.widthPixels / 6.102)),
				metrics.heightPixels, paint);

		paint.setColor(Color.GREEN);
		paint.setTextSize((int) (metrics.widthPixels / 39));
		canvas.drawText("Score:",
				(int) (metrics.widthPixels - (metrics.widthPixels / 7)),
				metrics.heightPixels / 8, paint);
		canvas.drawText(EnemyView.getGValues().getScore() + "",
				(int) (metrics.widthPixels - (metrics.widthPixels / 7)),
				(int) (metrics.heightPixels / 8 + Math
						.floor(metrics.heightPixels / 28.8)), paint);
		// canvas.drawText("Cash: 2000", (int)(metrics.widthPixels -
		// (metrics.widthPixels/7)), metrics.heightPixels/4, paint);

		paint.setColor(Color.RED);
		canvas.drawText("Health:",
				(int) (metrics.widthPixels - (metrics.widthPixels / 7)),
				metrics.heightPixels / 2, paint);
		canvas.drawText(EnemyView.getGValues().getHealth() + "/100",
				(int) (metrics.widthPixels - (metrics.widthPixels / 7)),
				(int) (metrics.heightPixels / 2 + Math
						.floor(metrics.heightPixels / 28.8)), paint);

		// Due to lack of time, bullet damage was not implemented
		// paint.setColor(Color.CYAN);
		// canvas.drawText("Damage:", (int)(metrics.widthPixels -
		// (metrics.widthPixels/7)), metrics.heightPixels/4*3, paint);
		// canvas.drawText(EnemyView.getgValues().getDamage() + "",
		// (int)(metrics.widthPixels - (metrics.widthPixels/7)),
		// (int)(metrics.heightPixels/4*3 +
		// Math.floor(metrics.heightPixels/28.8)), paint);
		invalidate();
	}
}
