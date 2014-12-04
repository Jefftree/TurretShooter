package com.example.turretshooter;

public class GameValues {

	// Score, damage, health values for sidebar.
	private int score, damage, health;

	public GameValues() {
		score = 0;
		damage = 0;
		health = 100;
	}

	public int getScore() {
		return score;
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
