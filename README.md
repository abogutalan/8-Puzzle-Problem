[Task] Implement A* tree search in Java that solves 8-puzzle with the misplaced-tiles heuristics.

path cost is constant and 1
path_cost(n) is depth

H(n)=8

Node -> ObjectPlus state
Game extends ObjectPlus

Goal state could be anything so read that from file instead of hard code it.

Abstract & Interface:
https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html


Windows:
javac -cp .; seach.jar Do8Puzzle.java
Try ->
javac -cp ".; seach.jar" Do8Puzzle.java

Compile on Mac:
javac -cp ".:search.jar" *.java

How to run:
>  java -cp ".:search.jar" Do8Puzzle InitGoalFile