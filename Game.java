
class Game extends ObjectPlus {
    private String state;
    private String move; // "L","R","U","D"

    public static void main(String[] args) {
        System.out.println("Game:");

    }

    String getState() {
        return this.state;
    }

    void setState(String currentState) {
        this.state = currentState;
    }

    String getMove() {
        return this.move;
    }
    
    void setMove(String direction) {
        this.move = direction;
    }

    // needs to be admissable (use misplaced tile function)
    int getHeuiristicValue(ObjectPlus goalState) {
        String goal = ((Game)goalState).getState();
        int h_of_n = 0;
        for (int i = 0; i < this.state.length(); i++) {
            if(this.state.charAt(i) != goal.charAt(i)) {
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
        System.out.print(this.state);
    }

    void showPart(int arg0) {
        if(arg0 < 0 || arg0 > 2) {
            System.out.println("Index should be between [0,2] to showPart!");
            return;
        }
        int index = arg0*6;
        for (int i = index; i < index+5; i++) {
            System.out.print(this.state.charAt(i));
        }
        
    }
}