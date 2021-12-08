package assignment1;
import java.util.*;

public class Driver {
	 public static void main(String[] args){
		 state initial_State = new state (3, 3, 0, 0, Position.LEFT);
		 bfs search = new bfs(initial_State);
		 state result = search.bfs_execution();
			List<state> path = new ArrayList<state>();
			state state_ = result;
			//System.out.print(state_.toString());
		 while(null!=state_) {
				path.add(state_);
				state_ = state_.getParentState();
			}
			
		 	System.out.println("Missionaries and Cannibals Problem:");
		    System.out.println("(Cannibal Left, Missionary Left, Cannibal Right, Missionary Right, Boat position)");

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state_ = path.get(i);
				if (state_.Final()) {
					System.out.print(state_.toString());
				} else {
					System.out.println(state_.toString() + " -> ");
				}
			}
	    }
}
