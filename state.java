package assignment1;
import java.util.*;

enum Position {RIGHT, LEFT}
public class state {
	private int cannibalLeft, cannibalRight, missionaryLeft, missionaryRight;
	private Position boat;
	private state parentState = null;
	
	public state(int cannibalLeft, int missionaryLeft, int cannibalRight, int missionaryRight,  Position boat) {
		this.cannibalLeft = cannibalLeft;
		this.missionaryLeft = missionaryLeft;
		this.cannibalRight = cannibalRight;
		this.missionaryRight = missionaryRight;
		this.boat = boat;	
	}

	public boolean invalid() {
		if (missionaryLeft < 0 || missionaryRight < 0 || cannibalLeft < 0 || cannibalRight < 0 || missionaryLeft + missionaryRight > 3 || cannibalLeft + cannibalRight > 3
	        || (missionaryLeft < cannibalLeft && missionaryLeft != 0) || (missionaryRight < cannibalRight && missionaryRight != 0)) 
		{
			return true;
		}
		return false;
	}

	public state getParentState() {
		return parentState;
	}
	
	private void Add_state(List<state> successors, state newState) {
		if (!newState.invalid()){
			newState.parentState=this;
			successors.add(newState);
		}
	}
	public List<state> generateSuccessors() {
		List<state> successors = new ArrayList<state>();
		if (boat == Position.LEFT)
		{	
			// One missionary and one cannibal cross left to right
			Add_state(successors, new state(cannibalLeft - 1, missionaryLeft - 1, cannibalRight + 1, missionaryRight + 1, Position.RIGHT));
			// One missionary crosses left to right
			Add_state(successors, new state(cannibalLeft, missionaryLeft - 1, cannibalRight, missionaryRight + 1, Position.RIGHT));
			// One cannibal crosses left to right
			Add_state(successors, new state(cannibalLeft - 1, missionaryLeft, cannibalRight + 1, missionaryRight, Position.RIGHT));
			// Two missionaries cross left to right
			Add_state(successors, new state(cannibalLeft, missionaryLeft - 2, cannibalRight, missionaryRight + 2, Position.RIGHT)); 
			// Two cannibals cross left to right
			Add_state(successors, new state(cannibalLeft - 2, missionaryLeft, cannibalRight + 2, missionaryRight, Position.RIGHT)); 			
		} else
		{
			// One missionary and one cannibal cross right to left
			Add_state(successors, new state(cannibalLeft + 1, missionaryLeft + 1, cannibalRight - 1, missionaryRight - 1, Position.LEFT));
			// One missionary crosses right to left
			Add_state(successors, new state(cannibalLeft, missionaryLeft + 1, cannibalRight, missionaryRight - 1, Position.LEFT));
			// One cannibal crosses right to left
			Add_state(successors, new state(cannibalLeft + 1, missionaryLeft, cannibalRight - 1, missionaryRight, Position.LEFT));
			// Two missionaries cross right to left
			Add_state(successors, new state(cannibalLeft, missionaryLeft + 2, cannibalRight, missionaryRight - 2, Position.LEFT)); 
			// Two cannibals cross right to left.
			Add_state(successors, new state(cannibalLeft + 2, missionaryLeft, cannibalRight - 2, missionaryRight, Position.LEFT)); 			
		}
		return successors;
	}
	public boolean Final() {
		return cannibalLeft == 0 && missionaryLeft == 0 && cannibalRight == 3 && missionaryRight == 3;
	}
	
	public String toString() {
		if (boat == Position.LEFT) {
			return "(" + cannibalLeft + "," + missionaryLeft + ","+ cannibalRight + "," + missionaryRight +  ",Left)";
		} else {
			return "(" + cannibalLeft + "," + missionaryLeft+ ","+ cannibalRight + "," + missionaryRight +  ",Right)";
		}
     }

}
