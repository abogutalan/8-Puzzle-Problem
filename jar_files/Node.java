package jar_files;

// Node.java: A search tree node.
import java.util.LinkedList;

class Node {
    Node parent;
    ObjectPlus state;
    int depth;
    int pathCost;
    // path cost from root
    Node child[] = null;

    // useful for tree printing
    // Create root node with state s.
    Node(ObjectPlus s) {
        parent = null;
        state = s;
        depth = 0;
    }

    // Create a node with state s, and parent p.
    Node(ObjectPlus s, Node p) {
        parent = p;
        state = s;
        depth = p.getDepth() + 1;
        pathCost = p.getPathCost() + s.getStepCost();
    }

    boolean isRootNode() {
        if (parent == null)
            return true;

        return false;
    }

    Node getParent() {
        return parent;
    }

    ObjectPlus getState() {
        return state;
    }

    int getDepth() {
        return depth;
    }

    int getPathCost() {
        return pathCost;
    }

    // Get path from root to this node in a list with the root as the head.
    LinkedList getPathFromRoot() {
        LinkedList<Node> ll = new LinkedList<Node>();
        Node current = this;
        while (!(current.isRootNode())) {
            ll.addFirst(current);
            current = current.getParent();
        }
        ll.addFirst(current); // take care of root node
        return ll;
    }

    // Given succeesor states of this node, add child nodes to tree.
    LinkedList stateToNode(LinkedList ss) {
        LinkedList<Node> nds = new LinkedList<Node>();
        int scnt = ss.size(); // # successor of this node
        child = new Node[scnt]; // successor pointers
        for (int i = 0; i < scnt; i++) {
            Node n = new Node((ObjectPlus) ss.get(i), this);// new succeesor node
            nds.add(n);
            child[i] = n; // point to successor
        }
        return nds;
    }

    void show() {
        System.out.println(" node at depth " + depth + " of state:");
        state.show();
    }
    
    // Show part of the node by index.
    void showPart(int index) {
        state.showPart(index);
    }
}