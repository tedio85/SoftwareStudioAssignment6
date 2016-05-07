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
	public final int X = 800;
	public final int Y = 200;
	private PApplet parent;
	private ArrayList<Character> characters;

	public Network(PApplet parent) {
		this.parent = parent;
		characters = new ArrayList<Character>();
	}

	public void display(){
		double angle = 2*Math.PI / characters.size();
		double curAngle = 0;
		double curX = X + RADIUS*2;
		double curY = Y + RADIUS;
		double cX = X + RADIUS;
		double cY = Y + RADIUS ;
		parent.stroke(204, 153, 0);
		parent.strokeWeight(4);
		parent.fill(255,0);
		parent.ellipse(X, Y, RADIUS*2, RADIUS*2);
		//change the (x, y) coordinates of the nodes
		for(Character ch : characters) {
			double shiftedX = curX - cX;
			double shiftedY = curY - cY;
			ch.setX((float)(shiftedX*Math.cos(curAngle) - shiftedY*Math.sin(curAngle)+cX));
			ch.setY((float)(shiftedX*Math.sin(curAngle) + shiftedY*Math.cos(curAngle)+cY));
			curAngle += angle;
		}
		//display the links
		parent.stroke(0);
		for(Character ch: characters) {
			for(Link link : ch.getTarget()) {
				parent.strokeWeight(link.getValue()/5);
				parent.curve(	ch.getX(), ch.getY(),
									 	ch.getX(), ch.getY(),
									 	(float) (this.X+RADIUS), (float)(this.Y+RADIUS),
									 	link.getCharacter().getX(), link.getCharacter().getY());
			}
				ch.display();	
		}
		
	}
	
	public void addToCircle(Character c) {
		c.getInCircle();
		characters.add(c);
	}
	
	public void removeFromCircle(Character c) {
		if(characters.contains(c)) {
			c.getOutCircle();
			characters.remove(c);
		}
	}
}
