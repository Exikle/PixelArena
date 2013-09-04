package com.xidstudios.pixelarena.tweenaccessors;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraTween implements TweenAccessor<OrthographicCamera> {

	public final static int POS_XY = 1;

	@Override
	public int getValues(OrthographicCamera target, int tweenType,
			float[] returnValues) {
		switch (tweenType) {
			case POS_XY:
				returnValues[0] = target.position.x;
				returnValues[1] = target.position.y;
				return 2;
		}
		return 0;
	}

	@Override
	public void setValues(OrthographicCamera target, int tweenType,
			float[] newValues) {
		switch (tweenType) {
			case POS_XY:
				target.position.set(newValues[0], newValues[1], 0);
				break;
		}
	}
}
