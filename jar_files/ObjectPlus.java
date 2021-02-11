package jar_files;

// ObjectPlus.java: A generic env state.

    
    abstract class ObjectPlus {

        
        // Get step cost from parent env state.
        abstract int getStepCost();

        // Show the state on screen.
        abstract void show();
        
        // For complex state, show part by index.
        abstract void showPart(int index);   
    }
   


    