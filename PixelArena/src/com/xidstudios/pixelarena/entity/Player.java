package com.xidstudios.pixelarena.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Player sprite that appears onto the screen
 * 
 * @author Dixon D'Cunha
 *         Extension of {@link Sprite}
 */
public class Player extends Sprite {

	public int direction = 1;

	public Direction currentDirection = Direction.DOWN;

	public static TextureRegion playerSpriteSheet;

	public float xD = 0, yD = 0;

	public int currentStep;

	public boolean keyDown = false;

	public final int STEP_DELAY = 15;

	public int counter = 0;

	OrthographicCamera camera;

	public Player(OrthographicCamera camera, TextureRegion spriteSheet) {
		this.camera = camera;
		// playerSpriteSheet = spriteSheet;
		playerSpriteSheet = new TextureRegion(new Texture(
				"imgs/MalePlayer.png"));
		changePlayerDirection(3);
	}

	public void changePlayerDirection(int d) {
		setDirection(d);
		switch (d) {
			case 1:// down
				playerSpriteSheet.setRegion(0,
						0 + (currentStep * 42), 37, 42);
				currentDirection = Direction.DOWN;
				break;
			case 2:// up
				playerSpriteSheet.setRegion(37,
						0 + (currentStep * 42), 37, 42);
				currentDirection = Direction.UP;
				break;
			case 3:// right
				playerSpriteSheet.setRegion(74,
						0 + (currentStep * 42), 37, 42);
				currentDirection = Direction.RIGHT;
				break;
			case 4:// left
				playerSpriteSheet.setRegion(111,
						0 + (currentStep * 42), 37, 42);
				currentDirection = Direction.LEFT;
				break;
		}
		this.setRegion(playerSpriteSheet);
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public void changeUserSteps() {
		counter++;
		if (counter == STEP_DELAY) {
			counter = 0;
			if (currentStep == 1) {
				setCurrentStep(2);
			} else {
				setCurrentStep(1);
			}
			changePlayerDirection(direction);
			// Gdx.app.log(ChromeGame.LOG, "Step " + step);
		}
	}

	public void moveUser() {
		// Vector2 oPos = new Vector2(this.getX(), this.getY());

		this.translate(xD, yD);
		if (keyDown) {
			changeUserSteps();
		}
		// Gdx.app.log(ChromeGame.LOG, this.getX() + "," + this.getY());
		camera.position.set(this.getX(), this.getY(), 0);

	}

	public void setXD(float x) {
		this.xD = x;
		keyDown = true;
	}

	public void setYD(float y) {
		this.yD = y;
		keyDown = true;
	}

	public void stopMovement() {
		yD = 0;
		xD = 0;
		keyDown = false;
		currentStep = 0;
		changePlayerDirection(direction);

	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
