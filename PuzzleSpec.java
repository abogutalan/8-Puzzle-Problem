import java.util.LinkedList;

public class PuzzleSpec extends Problem {

    private String problemDefinition;

    /**
	 * Constant which represents the no-op bit.
	 */
	private static final byte ACTION_NOOP	= 0;

	/**
	 * Constant which represents the "move up" bit.
	 */
	private static final byte ACTION_UP		= 1<<0;

	/**
	 * Constant which represents the "move down" bit.
	 */
	private static final byte ACTION_DOWN	= 1<<1;

	/**
	 * Constant which represents the "move left" bit.
	 */
	private static final byte ACTION_LEFT	= 1<<2;

	/**
	 * Constant which represents the "move right" bit.
	 */
	private static final byte ACTION_RIGHT	= 1<<3;

	/**
	 * Table which keeps bitsums for each location within the puzzle for the
	 * actions available at those locations.
	 */
	private static final byte[] AVAIL_ACTIONS = {
		ACTION_DOWN|ACTION_RIGHT,			ACTION_LEFT|ACTION_DOWN|ACTION_RIGHT,			ACTION_LEFT|ACTION_DOWN,
		ACTION_UP|ACTION_DOWN|ACTION_RIGHT,		ACTION_LEFT|ACTION_UP|ACTION_DOWN|ACTION_RIGHT,		ACTION_LEFT|ACTION_UP|ACTION_DOWN,
		ACTION_UP|ACTION_RIGHT,				ACTION_LEFT|ACTION_UP|ACTION_RIGHT,				ACTION_LEFT|ACTION_UP
	};


    String getProblemDefinition(){
        return this.problemDefinition;
    }
    void setProblemDefinition(String problemDefinition){
        this.problemDefinition = problemDefinition;
    }

    // https://github.com/collinsmith/8-Puzzle-Solver/blob/master/src/edu/csupomona/cs/cs420/project1/Main.java
    private static String swap(String successor_state, int i, int j) {
		if (i == j) {
			return "";
		}
        char[] c = successor_state.toCharArray();

        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;

        String swappedString = new String(c);

        return swappedString;
	}

    // Get successors given state s. note: generate successors of a given state
    LinkedList getSuccessor(ObjectPlus s) {
        
        String s_state = ((Game) s).getState();
        LinkedList<Game> successors = new LinkedList<Game>();
        
        String successor_state;
        int newBlankIndex=0;
        
        int BLANK_INDEX=0;
        for (int i = 0; i < s_state.length(); i++) {
            if (s_state.charAt(i) == '0') {
                BLANK_INDEX = i;
                break;
            }
        }

        int actions = AVAIL_ACTIONS[BLANK_INDEX/2];

        if ((actions&ACTION_LEFT) != 0) {
            Game successor = new Game();
            successor.setMove("<");
            newBlankIndex = BLANK_INDEX-2;
            successor_state = s_state;
            successor_state = swap(successor_state, BLANK_INDEX, newBlankIndex);
            successor.setState(successor_state);
            successors.addLast(successor);
        }

        if ((actions&ACTION_RIGHT) != 0) {
            Game successor = new Game();
            successor.setMove(">");
            newBlankIndex = BLANK_INDEX+2;
            successor_state = s_state;
            successor_state = swap(successor_state, BLANK_INDEX, newBlankIndex);
            successor.setState(successor_state);
            successors.addLast(successor);
        }
        
        if ((actions&ACTION_UP) != 0) {
            Game successor = new Game();
            successor.setMove("^");
            newBlankIndex = BLANK_INDEX-6;
            successor_state = s_state;
            successor_state = swap(successor_state, BLANK_INDEX, newBlankIndex);
            successor.setState(successor_state);
            successors.addLast(successor);
        }

        if ((actions&ACTION_DOWN) != 0) {
            Game successor = new Game();
            successor.setMove("v");
            newBlankIndex = BLANK_INDEX+6;
            successor_state = s_state;
            successor_state = swap(successor_state, BLANK_INDEX, newBlankIndex);
            successor.setState(successor_state);
            successors.addLast(successor);
        }
        
        // print successors list
        // for(int k=0;k<successors.size();k++){
        //     ((Game)successors.get(k)).show();
            
        // }

        return successors;
    }

    // Test if state s is a goal.
    boolean isGoalState(ObjectPlus s) {

        String goalState = ((Game) this.goalState).getState();
        String s_state = ((Game) s).getState();

        return goalState.equals(s_state);
    }
}
