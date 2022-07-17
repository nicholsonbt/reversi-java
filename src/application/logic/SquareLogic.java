package application.logic;

public class SquareLogic {
	private SquareColour colour;
	
	public SquareLogic() {
		this.colour = null;
	}
	
	public SquareLogic(SquareColour colour) {
		this.colour = colour;
	}
	
	public void SetColour(SquareColour colour) {
		this.colour = colour;
	}
	
	public SquareColour GetColour() {
		return this.colour;
	}
	
	public boolean IsEmpty() {
		return this.colour == null;
	}
	
	public SquareLogic copy() {
		SquareLogic newSquare = new SquareLogic();
		newSquare.SetColour(colour);
			
		return newSquare;
	}
}
