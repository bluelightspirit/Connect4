
public interface BoardInterface {

	public String toString();
	
	public boolean isValidMove(int column);
	
	public boolean play(int column, String color);
	
	public String checkWinner();

}
