package jar_files;

// SearchAgent.java: Tree search agent.
import java.util.LinkedList;

abstract class SearchAgent {
    Problem problem;
    LinkedList<Node> tree;
    LinkedList<Node> fringe;
    LinkedList solution;

    void setProblem(Problem p) {

        this.problem = p;
    }

    // Search for solution.
    // Post: value of solution is set.
    // Note: Search tree and fringe are maintained separately.
    // Each node has 1 copy and is referenced by both.
    void search() {
        Node root = new Node(problem.getInitialState());
        tree = new LinkedList<Node>(); // search tree
        fringe = new LinkedList<Node>(); // fringe note : to keep track of which nodes to expand next
        tree.add(root);
        insertFringe(root, fringe);
        while (fringe.size() != 0) {
            System.out.println("  Fringe size=" + fringe.size());
            Node n = (Node) fringe.removeFirst(); // node to visit
            System.out.print("Visit");
            n.show(); // show node
            ObjectPlus nodeState = n.getState();
            if (problem.isGoalState(nodeState)) {
                solution = n.getPathFromRoot();
                return;
            }
            LinkedList successors = problem.getSuccessor(nodeState); // child states
            LinkedList childnodes = n.stateToNode(successors); // child nodes
            for (int i = 0; i < childnodes.size(); i++) {
                tree.add(((Node) childnodes.get(i)));
                insertFringe((Node) childnodes.get(i), fringe);
            }
        }
        return;
    }

    abstract void showSolution();

    abstract void showTree();

    abstract void insertFringe(Node nd, LinkedList<Node> ll);
}