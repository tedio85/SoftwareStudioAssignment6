package main.java;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
	public int ver;
	private final float gap = 100;
	JSONObject data;
	JSONArray[] nodes = new JSONArray[8];
	JSONArray[] links = new JSONArray[8];
	private ArrayList<Character> characters;
	private ArrayList<Network> networks;
	private final static int width = 1200, height = 650;
	public void setup() {
		size(width, height);
		smooth();
		loadData();
	}
	public void draw() {
		background(255);
	}
	public void mouseDragged(){
		
	}
	public void keyPressed(){
		if (keyCode == KeyEvent.VK_1) ver = 1;
		else if (keyCode == KeyEvent.VK_2) ver = 2;
		else if (keyCode == KeyEvent.VK_3) ver = 3;
		else if (keyCode == KeyEvent.VK_4) ver = 4;
		else if (keyCode == KeyEvent.VK_5) ver = 5;
		else if (keyCode == KeyEvent.VK_6) ver = 6;
		else if (keyCode == KeyEvent.VK_7) ver = 7;
	}
	private void loadData(){
		for(int i = 1; i < 8; i++){
			v = i;
			result = path + String.format(file, v);
			System.out.println(result);
			data = loadJSONObject(result);
			nodes[i] = data.getJSONArray("nodes");
			links[i] = data.getJSONArray("links");
			for(int j = 0 ; j < nodes[i].size(); j++){
				int y = j;
				int x = 0;
				while(y >= 6) {
					y = y - 6;
					x = x + 1;
				}
				JSONObject temp = nodes[i].getJSONObject(j);
				characters.add(new Character(this, temp.getString("name"),10+gap*x,10+gap*y, temp.getInt("colour")));
			}
			for(int j = 0 ; j < links[i].size(); j++){
				JSONObject temp = links[i].getJSONObject(j);
				int source = temp.getInt("source");
				int target = temp.getInt("target");
				int value = temp.getInt("value");
				characters.get(source).addTarget(characters.get(target),value);
			}
		}
	}
}
