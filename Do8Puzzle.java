
import java.io.*;
import java.util.*;
class Do8Puzzle extends SearchAgent {

    private static Node root;
            

    public static void main(String[] args) throws Exception {
        System.out.println("   ~~~ Do8Puzzle ~~~");

        // reading problem definition from txt file.
        File file = new File(args[0]);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String problemDesc = "", initial = "", goal = "";
        int row = 0;
        String st;
        while ((st = br.readLine()) != null) {
            if ((row > 0) && (row < 4))
                initial += st.trim() + "\n";
            else if ((row > 5) && (row < 9))
                goal += st.trim() + "\n";
            problemDesc += st + "\n";
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

        // LinkedList<Game> successors = problem.getSuccessor(initialState);

        Do8Puzzle puzzle = new Do8Puzzle();
        puzzle.setProblem(problem);
        puzzle.search();


        System.out.println("\n\n ~~~~~ Show Tree ~~~~~ ");
        puzzle.showTree();

        System.out.println("\n ~~~~~ Solution to 8 Puzzle ~~~~~ ");
        puzzle.showSolution();
                

        // root = new Node(initialState);
        
    }

    // abstract classes
    
    void showSolution() {
        for(int i = 0; i < this.solution.size(); i++){
            ((Node)this.solution.get(i)).show();
        }

    }
  
    // to future me: Pls don't blame me for showTree funct. 
    // I didn't have enogh time to think lol  (Feb 11, 2021)
    void showTree() {
        
        int depth = 0;
        
        int max_depth = this.tree.getLast().pathCost; //2
        
        for(int d = 0; d < max_depth + 1; d++) {
            if(d==0) {
                ((Game)this.tree.get(0).getState()).show();
                System.out.println("  0\n");
            } 
            else {

                // showing to which parent node it's connected by numbers
                for(int j = 1; j < this.tree.size(); j++){
                    if(this.tree.get(j).depth == depth + 1) {
    
                        int p_index = this.tree.indexOf(this.tree.get(j).parent);
                        System.out.print("  "+p_index+ "     ");
                        
                    }
                }
                        System.out.println();

                // printing the 3x3 states according to their depth values.
                for(int i = 0; i < 3; i++){
                
                    for(int j = 1; j < this.tree.size(); j++){
                        if(this.tree.get(j).depth == depth + 1) {
        
                            ((Game)this.tree.get(j).getState()).showPart(i);
                            System.out.print(" | ");
                            
                        }
                        
        
                    }
                    System.out.println();
                }
                // printing index of each nodes below
                for(int j = 1; j < this.tree.size(); j++){
                    if(this.tree.get(j).depth == depth + 1) {
    
                        System.out.print("  " + j + "     ");
                        
                    }
                }

                System.out.println("\n");

                depth++;
            }

        }
    }
    
    void insertFringe(Node arg0, LinkedList<Node> arg1) {
        int current_heuristic = ((Game)arg0.getState()).getHeuiristicValue(this.problem.goalState);
        int g_val = arg0.pathCost;
        int f_val = current_heuristic + g_val;
        // System.out.println("current values: heuristic,"+current_heuristic+" / g,"+g_val+" / f,"+f_val);
        
        if(arg1.isEmpty()) {
            // System.out.println("test root");

            arg1.addFirst(arg0);
        } 
        
        else {
            // arg1.addLast(arg0);
            // System.out.println("test size of list: "+arg1.size());

            int heuristic = ((Game)arg1.getFirst().getState()).getHeuiristicValue(this.problem.goalState);
            int g = arg1.getFirst().pathCost;
            int f = heuristic + g;
            // System.out.println("head values: heuristic,"+heuristic+" / g,"+g+" / f,"+f);
            if(f_val <= f){
                // System.out.println("f_val: "+f_val);
                arg1.addFirst(arg0);
            }

        }
        
   


    }

    
}