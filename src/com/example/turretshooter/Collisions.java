package com.example.turretshooter;

import android.content.Context;

// Class is set to check for collisions
public class Collisions {

	// TurretView class to return Rect of turret used for collision
	private TurretView tView;

	public Collisions(Context context) {
		tView = new TurretView(context);
	}

	public void check() {

		// If bullet Rect intersects enemy Rect, then bullet is removed, enemy
		// is damaged
		if (BulletView.getBulletVector().isEmpty() == false
				&& EnemyView.getEnemyVector().isEmpty() == false) {
			for (int i = 0; i < BulletView.getBulletVector().size(); i++) {
				for (int j = 0; j < EnemyView.getEnemyVector().size(); j++) {
					if (BulletView
							.getBulletVector()
							.elementAt(i)
							.getBulletRect()
							.intersect(
									EnemyView.getEnemyVector().elementAt(j)
											.getEnemyRect())) {
						int eCap = EnemyView.getEnemyVector().elementAt(j)
								.getEnemyCap() - 1;
						EnemyView.getEnemyVector().elementAt(j)
								.setEnemyCap(eCap);
						BulletView.getBulletVector().elementAt(i)
								.setBulletCap(0);
					}
				}
			}
		}

		// If enemy hits turret wall, enemy is removed, health is decreased
		for (int j = 0; j < EnemyView.getEnemyVector().size(); j++) {
			if (EnemyView.getEnemyVector().elementAt(j).getEnemyRect()
					.intersect(tView.getTurretRect())
					|| EnemyView.getEnemyVector().elementAt(j).getEnemyRect()
							.intersect(tView.getWallRect())) {
				EnemyView.getEnemyVector().elementAt(j).setEnemyCap(0);
				EnemyView.getEnemyVector().elementAt(j).setEnemyType(0);
				EnemyView.getGValues().setHealth(
						EnemyView.getGValues().getHealth() - 5);
			}
		}
	}
}