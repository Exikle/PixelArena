package com.xidstudios.pixelarena;

import com.badlogic.gdx.math.Vector2;

public class Node {

	int hValue;

	int moveCostValue;

	int totalValue;

	Node parentNode;

	Vector2 parentVector;

	Node(Node node, Vector2 p) {
		if (p != null) {
			this.parentVector = new Vector2(p.x / 32, p.y / 32);
		}
		this.parentNode = node;
	}

	public void sethValue(int hValue) {
		this.hValue = hValue;
	}

	public void setMoveCost(int moveCostValue) {
		this.moveCostValue = moveCostValue;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public Vector2 getParentVector() {
		return parentVector;
	}

	public void setParentVector(Vector2 parentVector) {
		this.parentVector = parentVector;
	}
}