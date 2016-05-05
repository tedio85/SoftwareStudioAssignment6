package main.java;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	private final int radius = 30;
	private final int alpha = 70;
	private MainApplet parent;
	private boolean inCircle;
	private float x, y;
	private float originX, originY;
	private int color;
	private String name;
	
	
	
	public Character(MainApplet parent, String name, float x,  float y, int color){

		this.parent = parent;
		inCircle = false;
		this.name = name;
		this.originX = x;
		this.originY = y;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void display(){
		parent.stroke(0);
		parent.fill(color, alpha);
		parent.ellipse(x, y, radius, radius);
		parent.fill(color);
		parent.textSize(28);
		parent.text(name, x+radius*2+10, y);
	}
	
	public void getInCircle() {
		inCircle = true;
	}
	
	public void getOutCircle() {
		inCircle = false;
	}
	
	public boolean inCircle() {
		return inCircle;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getOriginX() {
		return originX;
	}
	
	public float getOriginY() {
		return originY;
	}
}
