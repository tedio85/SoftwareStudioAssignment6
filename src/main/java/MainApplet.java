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
	private final float gap = 80;
	JSONObject data;
	JSONArray nodes = new JSONArray();
	JSONArray links = new JSONArray();
	private ArrayList<Character> characters = new ArrayList<Character>();
	private Character hover_over_character, press_character;
	private boolean over_character = false;
	private Network network = new Network(this);
	private final static int width = 1200, height = 650;
	private ControlP5 cp5;
	public void setup() {
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
		for(Character c:characters){
			c.getInCircle();
			network.addToCircle(c);
		}
	}
	public void buttonB(){
		for(Character c:characters){
			c.getOutCircle();
			network.removeFromCircle(c);
		}
	}
	public void draw() {
		background(255);
		fill(100, 50, 25);
		text("Star Wars " + ver, 485, 50);
		for(Character c : characters){
			if(dist(c.getX(), c.getY(), mouseX, mouseY) < c.RADIUS && !mousePressed){
				hover_over_character = c;
				over_character = true;
			}
			if(c.inCircle() == false)	c.display();
		}
		network.display();
	}
	public void mouseDragged(){
		if(press_character != null){
			press_character.setX((float)mouseX);
			press_character.setY((float)mouseY);
		}
	}
	public void mousePressed() {
		if(over_character){
			press_character = hover_over_character;
		}
	}
	public void mouseReleased() {
		if(press_character != null){
			if(dist(mouseX, mouseY, network.X, network.Y) < network.RADIUS){
				press_character.getInCircle();
			}else {
				press_character.setX(press_character.getOriginX());
				press_character.setY(press_character.getOriginY());
				press_character.getOutCircle();
			}
		}
		press_character = null;
	}
	public void keyPressed(){
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
}
