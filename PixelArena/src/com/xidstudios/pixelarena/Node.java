package com.xidstudios.pixelarena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Node {

	int hValue;

	int moveCostValue;

	int totalValue;

	Node parentNode;

	Vector2 parentVector;

	Vector2 targetVector;

	Node(Node node, Vector2 p, Vector2 end) {
		if (p != null) {
			this.parentNode = node;
		}
		this.parentVector = new Vector2(p.x / 32, p.y / 32);
		this.targetVector = new Vector2(end.x / 32, end.y / 32);
		Gdx.app.log("Node", "Parent " + parentVector);
		Gdx.app.log("Node", "Target " + targetVector);
	}

	public Node(Node node, Vector2 p) {
		if (p != null) {
			this.parentNode = node;
		}
		this.parentVector = new Vector2(p.x / 32, p.y / 32);
		Gdx.app.log("Node", "" + parentVector);
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