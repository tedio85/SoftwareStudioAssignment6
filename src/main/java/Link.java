package main.java;

//represents a link between characters
public class Link {
	public Character c;	//character
	public int v;				//value
	
	public Link(Character ch, int value) {
		this.c = ch;
		this.v = value;
	}
	
	public Character getCharacter() {
		return c;
	}
	
	public int getValue() {
		return v;
	}
}
