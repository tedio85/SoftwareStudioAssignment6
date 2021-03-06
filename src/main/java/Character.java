package main.java;

import java.util.ArrayList;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	public final int RADIUS = 30;				//radius of each character disk
	private final int alpha = 150;				//transparency of each disk
	private MainApplet parent;
	private boolean inCircle;					//if the character is in the network
	private boolean showName;				//can show the name or not
	private float x, y;									//x, y coordinate
	private float originX, originY;				//the x, y coordinate when character is not in circle
	private int color;									
	private String name;
	private ArrayList<Link> targets;
	
	
	
	public Character(MainApplet parent, String name, float x,  float y, String colorStr){
		
		this.parent = parent;
		inCircle = false;
		showName = false;
		this.name = name;
		this.originX = x;
		this.originY = y;
		this.x = x;
		this.y = y;
		this.color =(int) Long.parseLong(colorStr.substring(1),16);
		this.targets = new ArrayList<Link>();
	}

	public void display(){					//show the character
		parent.stroke(0);
		parent.noStroke();
		parent.fill(color, alpha);
		parent.ellipse(x, y, RADIUS*2, RADIUS*2);
		parent.fill(color);
		if(showName) {
			parent.textSize(28);
			parent.text(name, x+RADIUS+10+10, y+5);
		}
	}
	
	public void addTarget(Character c, int value) {		//add target 
		targets.add(new Link(c, value));
	}
	
	public ArrayList<Link> getTarget() {
		return targets;
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
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getOriginX() {
		return originX;
	}
	
	public float getOriginY() {
		return originY;
	}
	
	public void showName() {
		showName = true;
	}
	
	public void hideName() {
		showName = false;
	}
	
	public String getName() {
		return name;
	}
}
