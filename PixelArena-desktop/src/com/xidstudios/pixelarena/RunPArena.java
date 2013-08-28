package com.xidstudios.pixelarena;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class RunPArena {

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = PArena.TITLE+" v"
				+ PArena.VERSION;
		cfg.useGL20 = true;
		cfg.width = 1080;
		cfg.height = 720;
		cfg.resizable = false;
		cfg.vSyncEnabled = true;

		new LwjglApplication(new PArena(), cfg);
	}
}
