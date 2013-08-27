/**
 * 
 */
package com.xidstudios.pixelarena;

import com.badlogic.gdx.graphics.Texture;

/**
 * @author Dixon D'Cunha
 */
public enum Graphic {
	XID_LOGO("imgs/XidStudios.png"), EXIKLE_LOGO("imgs/Exikle.png"), STARTBG("imgs/StartBG.png");

	String parent = "imgs/";

	String filePath;

	private Texture texture;

	private boolean graphicLoaded;

	private Graphic(String imagePath) {
		filePath = imagePath;
		graphicLoaded = false;

	}

	public Texture getTexture() {
		if (!graphicLoaded) {
			loadGraphic();
		}
		return texture;
	}

	public void loadGraphic() {
		texture = new Texture(filePath);
		graphicLoaded = true;
	}
}
