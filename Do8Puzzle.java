
import java.io.*;
class Do8Puzzle extends SearchAgent {


    public static void main(String[] args) throws Exception {
        System.out.println("   ~~~ Do8Puzzle ~~~");


        // reading problem definition from txt file.
        File file = new File(args[0]);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String problemDesc = "", initial = "", goal = "";
        int row = 0;
        String st;
        while ((st = br.readLine()) != null) {
            if((row > 0) && (row < 4))  initial += st.trim() + "\n";
            else if((row > 5) && (row < 9)) goal += st.trim() + "\n";
            problemDesc+=st+"\n";
            row++;
        }
        System.out.println(problemDesc);
        // populating game
        Game initialState = new Game();
        Game goalState = new Game();
        initialState.setState(initial);
        goalState.setState(goal);
    
        // populating puzzle
        PuzzleSpec problem = new PuzzleSpec();
        problem.setProblemDefinition(problemDesc);
        problem.setInitialState(initialState);
        problem.setGoalState(goalState);
        problem.setStrategy("Strategy: A* tree search");

        problem.getSuccessor(initialState);
        
        Do8Puzzle puzzle = new Do8Puzzle();
        puzzle.setProblem(problem);
        puzzle.search();
        
    }

    // abstract classes
    
    void showSolution() {

    }
  
    void showTree() {

    }
    
    void insertFringe(Node arg0, java.util.LinkedList<Node> arg1) {

    }

    
}