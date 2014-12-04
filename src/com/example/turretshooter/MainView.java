package com.example.turretshooter;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class MainView extends View {

	// collaboration of all the different classes and views implemented
	private BackgroundView background;
	private TurretView turret;
	private BulletView bullet;
	private SidebarView sidebar;
	private StartupView startup;
	private EnemyView enemy;
	private Collisions collisions;

	public MainView(Context context) {
		super(context);
		startup = new StartupView(context);
		background = new BackgroundView(context);
		turret = new TurretView(context);
		bullet = new BulletView(context);
		sidebar = new SidebarView(context);
		enemy = new EnemyView(context);
		collisions = new Collisions(context);
	}

	// onTouchEvent for everything
	public boolean onTouchEvent(MotionEvent e) {
		if (startup.getStartGame() == false) {
			startup.onTouchEvent(e);
		} else {
			turret.onTouchEvent(e);
			bullet.onTouchEvent(e);
		}
		invalidate();
		return true;
	}

	// onDraw event for all views
	public void onDraw(Canvas canvas) {
		if (startup.getStartGame() == false
				|| EnemyView.getGValues().getHealth() <= 0) {
			EnemyView.getGValues().setHealth(100);
			EnemyView.getGValues().setScore(0);
			startup.onDraw(canvas);
		} else {
			collisions.check();
			background.onDraw(canvas);
			turret.onDraw(canvas);
			bullet.onDraw(canvas);
			sidebar.onDraw(canvas);
			enemy.onDraw(canvas);
		}
		invalidate();
	}
}
