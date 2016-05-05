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
	
	private PApplet parent;
	private ArrayList<Character> characters;

	public Network(PApplet parent) {
		this.parent = parent;
		characters = new ArrayList<Character>();
	}

	public void display(){
		
	}
	
	public void addToCircle(Character c) {
		characters.add(c);
	}
	
}
