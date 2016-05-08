package main.java;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import controlP5.ControlP5;
/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet {
	private String path = "main/resources/";
	private String file = "starwars-episode-%d-interactions.json";
	private int v;
	private String result;
	public int ver = 1;
	private final float gap = 75;
	JSONObject data;
	JSONArray nodes = new JSONArray();
	JSONArray links = new JSONArray();
	private ArrayList<Character> characters;
	private static Character hover_over_character, press_character;
	private boolean over_character = false;
	private Network network = new Network(this);
	private final static int width = 1200, height = 650;
	private ControlP5 cp5;
	public void setup() {
		//in setup initiate button and load data
		size(width, height);
		smooth();
		loadData();
		cp5 = new ControlP5(this);
		cp5.addButton("buttonA")
		.setLabel("ADD ALL")
		.setPosition(3*width/4+50, 2*height/3)
		.setSize(200, 50);
		cp5.addButton("buttonB")
		.setLabel("CLEAR")
		.setPosition(3*width/4+50, 2*height/3+70)
		.setSize(200, 50);
	}
	public void buttonA(){
		//button A's function is add all character which is not in the circle
		for(Character c:characters){
				network.addToCircle(c);
		}
	}
	public void buttonB(){
		//button B's function is to remove all character within the circle
		//and put them back to their original spot
		for(Character c:characters){
				network.removeFromCircle(c);
		}
		for(Character c:characters){
			c.setX(c.getOriginX());
			c.setY(c.getOriginY());
		}
		
	}
	public void draw() {
		//all characters' name is hidden and will only display when the mouse is
		//hovering over it
		//done by using the distance of the circle and mouse to determine
		//whether it is on the circle or not
		//and if yes, the character will be saved into hover_over_character
		//which will be used to determine a lot of other stuff (below function)
		//the hover_over_character will use the showName() function in Character.java
		//to show its name
		//all character not within the circle will be displayed to the side as default
		//and all character within the circle will be displayed by network.display()
		over_character = false;
		hover_over_character = null;
		background(255);
		fill(100, 50, 25);
		text("Star Wars " + ver, 485, 50);
		for(Character c : characters){
				c.hideName();
			if(dist(c.getX(), c.getY(), mouseX, mouseY) < c.RADIUS && !mousePressed){
				hover_over_character = c;
				over_character = true;
				hover_over_character.showName();
			}
			if(c.inCircle() == false)	c.display();
		}
		network.display();
	}
	public void mousePressed() {
		//as described in the above function
		//if hover_over_character, set over_character to true and
		//save a press_character for the mouse_dragged event
		if(over_character){
			press_character = hover_over_character;
			over_character = false;
		}
	}
	public void mouseDragged(){
		//if mouse pressed event saves a press_character (not null)
		//then this can work...
		if(press_character != null){
			press_character.setX((float)mouseX);
			press_character.setY((float)mouseY);
		}
	}

	public void mouseReleased() {
		//need to determine the distance between mouse(if dragging character) 
		//and the big circle
		//to check whether it is in the circle or not
		//if yes, give it the getInCircle() function and add it the network
		//if no, put it back to its original place and get it out of network
		if(press_character != null){
			if(dist(mouseX, mouseY, network.X, network.Y) < network.RADIUS){
				network.addToCircle(press_character);
			}else {
				network.removeFromCircle(press_character);
				press_character.setX(press_character.getOriginX());
				press_character.setY(press_character.getOriginY());
			}
		}
		press_character = null;
		hover_over_character = null;
	}
	public void keyPressed(){
		//easy stuff
		//press number, change version
		//every time you change the version
		//need to load data and new a network so the screen can be 
		//cleared of the previous one
		if (keyCode == KeyEvent.VK_1 && ver != 1) {
			ver = 1;
			loadData();
			network = new Network(this);
			
		}
		else if (keyCode == KeyEvent.VK_2 && ver != 2) {
			ver = 2;
			loadData();
			network = new Network(this);
		}
		else if (keyCode == KeyEvent.VK_3 && ver != 3) {
			ver = 3;
			loadData();
			network = new Network(this);
		}
		else if (keyCode == KeyEvent.VK_4 && ver != 4) {
			ver = 4;
			loadData();
			network = new Network(this);
		}
		else if (keyCode == KeyEvent.VK_5 && ver != 5) {
			ver = 5;
			loadData();
			network = new Network(this);
		}
		else if (keyCode == KeyEvent.VK_6 && ver != 6) {
			ver = 6;
			loadData();
			network = new Network(this);
		}
		else if (keyCode == KeyEvent.VK_7 && ver != 7) {
			ver = 7;
			loadData();
			network = new Network(this);
		}
	}
	private void loadData(){
		//load data as in lab
		//save it to object, run its size while taking out the data one by one
		//the x, y coordinate is determined by the order which it is read
		//and different from lab, we modified the function a bit so that
		//the source connects to both target and the value to show their connection
			characters = new ArrayList<Character>();
			v = ver;
			result = path + String.format(file, v);
			System.out.println(result);
			data = loadJSONObject(result);
			nodes = data.getJSONArray("nodes");
			links = data.getJSONArray("links");
			for(int j = 0 ; j < nodes.size(); j++){
				int y = j;
				int x = 0;
				while(y >= 8) {
					y = y - 8;
					x = x + 1;
				}
				JSONObject temp = nodes.getJSONObject(j);
				//System.out.println(temp.getString("name"));
				//System.out.println(temp.getString("colour"));
				characters.add(new Character(this, temp.getString("name"),(float)40+gap*x,(float)40+gap*y, temp.getString("colour")));
			}
			for(int j = 0 ; j < links.size(); j++){
				JSONObject temp = links.getJSONObject(j);
				int source = temp.getInt("source");
				int target = temp.getInt("target");
				int value = temp.getInt("value");
				characters.get(source).addTarget(characters.get(target),value);
			}
	}
	
	public static Character getPressCharacter() {
		return press_character;
	}
}
