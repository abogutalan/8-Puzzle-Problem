
class Game extends ObjectPlus {
    private String state;
    private String move; // "L","R","U","D"
    private int heuristic;
    private int g_of_n;
    private int f_of_n;

    String getState() {
        return this.state;
    }

    void setState(String currentState) {
        this.state = currentState;
    }

    int getHeuristic() {
        return this.heuristic;
    }

    void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    int get_g_of_n() {
        return this.g_of_n;
    }

    void set_g_of_n(int g) {
        this.g_of_n = g;
    }

    int get_f_of_n() {
        return this.f_of_n;
    }

    void set_f_of_n(int f) {
        this.f_of_n = f;
    }

    String getMove() {
        return this.move;
    }

    void setMove(String direction) {
        this.move = direction;
    }

    // needs to be admissable (use misplaced tile function)
    int getHeuiristicValue(ObjectPlus goalState) {
        String goal = ((Game) goalState).getState();
        int h_of_n = 0;
        for (int i = 0; i < this.state.length(); i++) {
            if (this.state.charAt(i) != goal.charAt(i)) {
                h_of_n++;
            }
        }

        return h_of_n;
    }

    // abstract classes

    // parent state to current state
    int getStepCost() {
        // always 1 for 8-puzzle
        return 1;
    }

    void show() {
        if (this.getMove() != null)
            System.out.println("Move: " + this.getMove());
        System.out.println(this.state);
    }

    void showPart(int arg0) {
        if (arg0 < 0 || arg0 > 2) {
            System.out.println("Index should be between [0,2] to showPart!");
            return;
        }
        int index = arg0 * 6;
        for (int i = index; i < index + 5; i++) {
            System.out.print(this.state.charAt(i));
        }

    }
}