package application.logic;

public class Square {
	private Colour colour;
	
	public Square() {
		this.colour = null;
	}
	
	public Square(Colour colour) {
		this.colour = colour;
	}
	
	public void SetColour(Colour colour) {
		this.colour = colour;
	}
	
	public Colour GetColour() {
		return this.colour;
	}
	
	public boolean IsEmpty() {
		return this.colour == null;
	}
}
