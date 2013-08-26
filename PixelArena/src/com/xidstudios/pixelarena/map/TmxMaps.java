package com.xidstudios.pixelarena.map;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public enum TmxMaps {
	AreaOne(1, "AREA ONE", "maps/AreaOne.tmx");

	int id;

	String name;

	String path;

	TmxMaps(int id, String name, String path) {
		this.id = id;
		this.name = name;
		this.path = path;
	}

	int getID() {
		return id;
	}

	String getName() {
		return name;
	}

	Map loadMap() {
		Map map = new TmxMapLoader().load(path);
		return map;
	}

}
