
import java.io.*;
import java.util.*;
class Do8Puzzle extends SearchAgent {

    public static void main(String[] args) throws Exception {
        System.out.println(" ~~~~~~~~~~ Do8Puzzle ~~~~~~~~~~ \n");

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


        Do8Puzzle puzzle = new Do8Puzzle();
        puzzle.setProblem(problem);
        System.out.println(" ~~~~~~~~~~ Insert Fringe ~~~~~~~~~~ \n");
        puzzle.search();


        System.out.println("\n\n ~~~~~~~~~~ Show Tree ~~~~~~~~~~ \n");
        puzzle.showTree();

        System.out.println("\n ~~~~~~~~~~ Solution to 8 Puzzle ~~~~~~~~~~ \n");
        puzzle.showSolution();
                

        
    }

    // abstract classes
    
    void showSolution() {
        ArrayList<String> sequenceOfSolution = new ArrayList<>();
        for(int i = 0; i < this.solution.size(); i++){
            ((Node)this.solution.get(i)).show();
            sequenceOfSolution.add(((Game)((Node)this.solution.get(i)).state).getMove());
        }
        String solution = "( ";
        for(int j = 0; j < sequenceOfSolution.size(); j++){
            String move = sequenceOfSolution.get(j);
            if (move != null) {
                if(move.equals("<")) solution += "Left";
                else if(move.equals(">")) solution += "Right";
                else if(move.equals("^")) solution += "Up";
                else if(move.equals("v")) solution += "Down";

                if (j != sequenceOfSolution.size()-1) solution += ", ";
            }
            
            
        }
        System.out.println("Action sequence for solution: \n" + solution + " )");

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
                        System.out.print("  "+p_index+ "        ");
                        
                    }
                }
                        System.out.println();

                // printing the 3x3 states according to their depth values.
                for(int i = 0; i < 3; i++){
                
                    for(int j = 1; j < this.tree.size(); j++){
                        if(this.tree.get(j).depth == depth + 1) {
        
                            ((Game)this.tree.get(j).getState()).showPart(i);
                            
                            if(i==0) System.out.print(" (g" + ((Game)this.tree.get(j).getState()).get_g_of_n() + ") ");
                            else if(i==1) System.out.print(" (h" + ((Game)this.tree.get(j).getState()).getHeuristic() + ") ");
                            else if(i==2) System.out.print(" (f" + ((Game)this.tree.get(j).getState()).get_f_of_n() + ") ");
                            
                        }
                        
        
                    }
                    System.out.println();
                }
                // printing index of each nodes below
                for(int j = 1; j < this.tree.size(); j++){
                    if(this.tree.get(j).depth == depth + 1) {
    
                        System.out.print("  " + j + "        ");
                        
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
        ((Game)arg0.getState()).setHeuristic(current_heuristic);
        ((Game)arg0.getState()).set_g_of_n(g_val);
        ((Game)arg0.getState()).set_f_of_n(f_val);
        
        if(arg1.isEmpty()) {

            arg1.addFirst(arg0);
        } 
        
        else {

            int heuristic = ((Game)arg1.getFirst().getState()).getHeuiristicValue(this.problem.goalState);
            int g = arg1.getFirst().pathCost;
            int f = heuristic + g;
            if(f_val <= f){
                arg1.addFirst(arg0);
            }

        }
        

    }

    
}