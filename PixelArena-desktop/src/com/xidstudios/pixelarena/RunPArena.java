package com.xidstudios.pixelarena;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class RunPArena {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Pixel Arena  - Xid Studios " + MainPArena.VERSION;
		cfg.useGL20 = true;
		cfg.width = 1080;
		cfg.height = 720;

		new LwjglApplication(new MainPArena(), cfg);
	}
}
