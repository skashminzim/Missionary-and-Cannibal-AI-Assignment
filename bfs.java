package assignment1;
import java.util.*;

public class bfs {
	state initialState;
	Queue<state> nodes;
	Set<state> explored;
	public bfs(state initialState_){
		initialState = initialState_;
		nodes = new LinkedList<state>();
		explored = new HashSet<state>();
	}
	public state bfs_execution() {
			if (initialState.Final()) {
				return initialState;
			}
			else {
		nodes.add(initialState);
		while (true) {
			if (nodes.isEmpty()) {
				return null;
			}
			state state1 = nodes.poll();
			explored.add(state1);
			List<state> successors = state1.generateSuccessors();
			for (state child : successors) {
					if (child.Final()) {
						return child;
					}
					if (!explored.contains(child)|| !nodes.contains(child)){
					nodes.add(child);}
				}
			}
		}
	}
}