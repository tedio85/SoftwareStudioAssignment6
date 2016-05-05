package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	public final int RADIUS = 250;
	public final int X = 400;
	public final int Y = 150;
	private PApplet parent;
	private ArrayList<Character> characters;

	public Network(PApplet parent) {
		this.parent = parent;
		characters = new ArrayList<Character>();
	}

	public void display(){
		double angle = 2*Math.PI / characters.size();
		double curAngle = 0;
		double curX = X + RADIUS;
		double curY = Y;
		parent.stroke(204, 153, 0);
		parent.ellipse(X, Y, RADIUS*2, RADIUS*2);
		//change the (x, y) coordinates of the nodes
		for(Character ch : characters) {
			ch.setX((float)(curX*Math.cos(curAngle) - curY*Math.sin(curY)));
			ch.setY((float)(curX*Math.sin(curAngle) + curY*Math.cos(curY)));
		}
		//display the nodes
		for(Character ch: characters) {
			ch.display();
		}
		
	}
	
	public void addToCircle(Character c) {
		characters.add(c);
	}
	
}
