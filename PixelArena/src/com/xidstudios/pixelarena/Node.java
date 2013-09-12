package com.xidstudios.pixelarena;

import com.badlogic.gdx.math.Vector2;

public class Node {

	int hValue;

	int moveCostValue;

	int totalValue;

	int id;

	Node parentNode;

	Vector2 parentVector;

	Node(Node node, Vector2 p) {
		if (p != null) {
			this.parentVector = new Vector2(p.x / 32, p.y / 32);
		}
		this.parentNode = node;
	}

	/**
	 * @return the hValue
	 */
	public int gethValue() {
		return hValue;
	}

	/**
	 * @param Set
	 *            the hValue with hValue
	 */
	public void sethValue(int hValue) {
		this.hValue = hValue;
	}

	/**
	 * @return the gValue
	 */
	public int getMoveCost() {
		return moveCostValue;
	}

	/**
	 * @param Set
	 *            the gValue with gValue
	 */
	public void setMoveCost(int moveCostValue) {
		this.moveCostValue = moveCostValue;
	}

	/**
	 * @return the totalValue
	 */
	public int getTotalValue() {
		return totalValue;
	}

	/**
	 * @param Set
	 *            the totalValue with totalValue
	 */
	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param Set
	 *            the id with id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the parentNode
	 */
	public Node getParentNode() {
		return parentNode;
	}

	/**
	 * @param Set
	 *            the parentNode with parentNode
	 */
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * @return the parentVector
	 */
	public Vector2 getParentVector() {
		return parentVector;
	}

	/**
	 * @param Set
	 *            the parentVector with parentVector
	 */
	public void setParentVector(Vector2 parentVector) {
		this.parentVector = parentVector;
	}
}