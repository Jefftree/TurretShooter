package com.example.turretshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

// Class is meant to define a set of values for bullets
public class BulletValues extends Object {

	// Bitmaps for bullet
	private Bitmap panelBullet;

	// Canvas for bullet
	private Canvas bullet;

	// Rectangle for bullet collision purposes
	private Rect bulletRect;

	// Bullet rotation variable
	private int rotation;

	// Bullet time variables for animation purposes
	private int time, time2;

	// Defines the center of pivot for bullets
	private int centerLocX;
	private int centerLocY;

	// Boolean to signify which bullet is being shot
	private boolean isTopBullet;

	// Defines the life of the bullet
	private int bulletCap;

	BulletValues() {

	}

	BulletValues(Bitmap pB, Canvas b, Rect bR, int rot, int t, int t2, int cY,
			int cX, boolean iTB) {
		panelBullet = pB;
		bullet = b;
		bulletRect = bR;
		rotation = rot;
		time = t;
		time2 = t2;
		centerLocX = cX;
		centerLocY = cY;
		isTopBullet = iTB;
	}

	public int getCenterLocX() {
		return centerLocX;
	}

	public int getCenterLocY() {
		return centerLocY;
	}

	public Bitmap getPanelBullet() {
		return panelBullet;
	}

	public Canvas getBullet() {
		return bullet;
	}

	public Rect getBulletRect() {
		return bulletRect;
	}

	public int getRotation() {
		return rotation;
	}

	public int getTime() {
		return time;
	}

	public int getTime2() {
		return time2;
	}

	public boolean getIsTopBullet() {
		return isTopBullet;
	}

	public int getBulletCap() {
		return bulletCap;
	}

	public void setCenterLocX(int centerLocX) {
		this.centerLocX = centerLocX;
	}

	public void setCenterLocY(int centerLocY) {
		this.centerLocY = centerLocY;
	}

	public void setPanelBullet(Bitmap panelBullet) {
		this.panelBullet = panelBullet;
	}

	public void setBullet(Canvas bullet) {
		this.bullet = bullet;
	}

	public void setBulletRect(Rect bulletRect) {
		this.bulletRect = bulletRect;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setTime2(int time2) {
		this.time2 = time2;
	}

	public void setIsTopBullet(boolean isTopBullet) {
		this.isTopBullet = isTopBullet;
	}

	public void setBulletCap(int bCap) {
		bulletCap = bCap;
	}
}
