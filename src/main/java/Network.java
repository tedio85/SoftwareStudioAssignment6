package main.java;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	public final int RADIUS = 250;		//radius of the large circle
	public final int X = 800;					//x coordinate of the center
	public final int Y = 300;					//y coordinate of the center
	private PApplet parent;
	private ArrayList<Character> characters;		//characters in the circle

	public Network(PApplet parent) {
		this.parent = parent;
		characters = new ArrayList<Character>();
		Ani.init(parent);
	}

	public void display(){
		double angle = 2*Math.PI / characters.size();		//angle between two characters
		double curAngle = 0;												//current angle rotated
		double curX = X + RADIUS*2;								//x coordinate of the first point to be put on circle
		double curY = Y + RADIUS;									//y coordinate of the first point to be put on circle
		double cX = X + RADIUS;
		double cY = Y + RADIUS ;
		parent.stroke(204, 153, 0);
		parent.strokeWeight(4);
		parent.fill(255,0);
		parent.ellipse(X, Y, RADIUS*2, RADIUS*2);			//draw the circle
		//change the (x, y) coordinates of the nodes
		for(Character ch : characters) {								//set the x,y coordinate of all points in circle
			double shiftedX = curX - cX;
			double shiftedY = curY - cY;
			ch.setX((float)(shiftedX*Math.cos(curAngle) - shiftedY*Math.sin(curAngle)+X));
			ch.setY((float)(shiftedX*Math.sin(curAngle) + shiftedY*Math.cos(curAngle)+Y));
			curAngle += angle;
		}
		//display the links
		parent.stroke(0);
		for(Character ch: characters) {
			for(Link link : ch.getTarget()) {
				if(link.getCharacter().inCircle() == true) {
					parent.noFill();
					parent.stroke(0);
					parent.strokeWeight(link.getValue()/5+1);
					parent.bezier(ch.getX(), ch.getY(),
										 	(ch.getX()+X)/2, (ch.getY()+Y)/2,
										 	(link.getCharacter().getX()+X)/2, (link.getCharacter().getY()+Y)/2,
										 	link.getCharacter().getX(), link.getCharacter().getY());
				}
			}
				ch.display();	
		}
		
	}
	
	public void addToCircle(Character c) {			//add to the network
		if(!characters.contains(c)) {
			c.getInCircle();
			characters.add(c);
		}
	}
	
	public void removeFromCircle(Character c) {		//remove from the network
		if(characters.contains(c)) {
			c.getOutCircle();
			characters.remove(c);
			Ani.to(c, (float) 1, "x", c.getOriginX());
			Ani.to(c, (float) 1, "y", c.getOriginY());
		}
	}
}
