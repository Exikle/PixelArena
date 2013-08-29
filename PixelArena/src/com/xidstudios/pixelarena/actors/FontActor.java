package com.xidstudios.pixelarena.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.xidstudios.pixelarena.GameFont;

public class FontActor extends Actor {

	private BitmapFont font;

	private final float mid = Gdx.graphics.getWidth() / 2;

	public FontActor() {
		font = GameFont.fWhite;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		String string = "";
		switch (Gdx.app.getType()) {
			case Android:
				string = "Tap to Start";
				break;
			case Desktop:
				string = "Press to Start";
				break;
			default:
				break;
		}
		font.setScale(2.3f);
		font.draw(batch, string, mid - font.getBounds(string).width
				/ 2, 0);
	}

}
