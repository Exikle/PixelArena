package com.xidstudios.pixelarena.tweenaccessors;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.xidstudios.pixelarena.arena.Arena;

public class SpriteTween implements TweenAccessor<Sprite> {

	public static final int POS_XY = 1;

	public static final int ALPHA = 2;

	private Arena arena;

	public SpriteTween(Arena arena) {
		this.arena = arena;
	}

	public SpriteTween() {}

	@Override
	public int getValues(Sprite target, int tweenType,
			float[] returnValues) {
		switch (tweenType) {
			case POS_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;
			case ALPHA:
				returnValues[0] = target.getColor().a;
				return 1;
			default:
				return 0;
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType,
			float[] newValues) {
		switch (tweenType) {
			case POS_XY:
				target.setPosition(newValues[0], newValues[1]);
				if (arena != null) {
//					arena.oX = (int) target.getX();
//					arena.oY = (int) target.getY();
				}
				break;
			case ALPHA:
				target.setColor(1, 1, 1, newValues[0]);
				break;
		}
	}
}