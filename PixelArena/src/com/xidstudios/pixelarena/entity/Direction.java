package com.xidstudios.pixelarena.entity;

/**
 * The direction the user is facing
 */
public enum Direction {

	/**
	 * The player facing down
	 */
	DOWN(1, -2, "VERTICAL"),
	/**
	 * The player facing up
	 */
	UP(2, 2, "VERTICAL"),
	/**
	 * The player facing right
	 */
	RIGHT(3, 2, "HORIZONTAL"),
	/**
	 * The player facing left
	 */
	LEFT(4, -2, "HORIZONTAL");

	/**
	 * the speed of the player
	 */
	float speed;

	/**
	 * The int value of the direction the player is facing
	 */
	int directionVal;

	/**
	 * The axis the direction moves on
	 */
	String axis;

	/**
	 * Constructor
	 * 
	 * @param Direction
	 *            value
	 * @param Speed
	 * @param Axis
	 *            of the player
	 */
	Direction(int d, float s, String a) {
		speed = s;
		directionVal = d;
		axis = a;
	}

	/**
	 * Gets the speed
	 * 
	 * @return speed
	 */
	public float getSpeed() {
		return speed;

	}

	/**
	 * Gets the directions value
	 * 
	 * @return direction's value
	 */
	public int getDirection() {
		return directionVal;

	}

	public String getXis() {
		return axis;

	}
}
