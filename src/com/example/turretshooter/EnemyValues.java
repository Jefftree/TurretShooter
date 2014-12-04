package com.example.turretshooter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

// Class is meant to define a set of values for enemies
public class EnemyValues {

	// Bitmaps for enemy
	private Bitmap panelEnemy;

	// Canvas for enemy
	private Canvas Enemy;

	// Rectangle for enemy collision purposes
	private Rect enemyRect;

	// Enemy time variable for animation purposes
	private int time;

	// X location of enemy of top left corner
	private int PosX;

	// Set to define damage that enemy health
	private int enemyCap;

	// Set to define the enemy type
	private int enemyType;

	public EnemyValues() {

	}

	public Bitmap getPanelEnemy() {
		return panelEnemy;
	}

	public Canvas getEnemy() {
		return Enemy;
	}

	public Rect getEnemyRect() {
		return enemyRect;
	}

	public int getTime() {
		return time;
	}

	public int getPosX() {
		return PosX;
	}

	public int getEnemyCap() {
		return enemyCap;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public void setPanelEnemy(Bitmap panelEnemy) {
		this.panelEnemy = panelEnemy;
	}

	public void setEnemy(Canvas enemy) {
		Enemy = enemy;
	}

	public void setEnemyRect(Rect enemyRect) {
		this.enemyRect = enemyRect;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setPosX(int posX) {
		PosX = posX;
	}

	public void setEnemyCap(int eCap) {
		enemyCap = eCap;
	}

	public void setEnemyType(int eType) {
		enemyType = eType;
	}
}
