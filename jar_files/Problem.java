package jar_files;

// Problem.java: Problem for tree search.
import java.util.LinkedList;

abstract class Problem {
    ObjectPlus initialState, goalState;
    String strategy; // search strategy note: Like BFS or visit node in fringe from left ot right etc.

    ObjectPlus getInitialState() {
        return initialState;
    }

    void setInitialState(ObjectPlus s) {
        this.initialState = s;
    }

    ObjectPlus getGoalState() {
        return goalState;
    }

    void setGoalState(ObjectPlus s) {
        this.goalState = s;
    }

    String getStrategy() {
        return new String(strategy);
    }

    void setStrategy(String stra) {
        strategy = new String(stra);
    }

    // Abstract methods

    // Get successors given state s. note: generate successors of a given state
    abstract LinkedList getSuccessor(ObjectPlus s);
    
    // Test if state s is a goal.
    abstract boolean isGoalState(ObjectPlus s);
}