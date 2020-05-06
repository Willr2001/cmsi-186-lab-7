import java.util.Stack;

public class BacktrackingMazeSolver {


    public boolean solve(Maze maze, Maze.MazeListener listener) {

        if (listener == null){
          throw new IllegalArgumentException("Listener cannot be null");
        }

        var path = new Stack<Maze.Location>();
        var current = maze.getInitialRatPosition();


        while (true) {

            current.place(Maze.Cell.RAT);
            listener.mazeChanged(maze);


            if (current.isAt(maze.getInitialCheesePosition())) {
                return true;
            }


            if (current.above().canBeMovedTo()) {
                path.push(current);
                current.place(Maze.Cell.PATH);
                current = current.above();
            } else if (current.toTheRight().canBeMovedTo()) {

                path.push(current);
                current.place(Maze.Cell.PATH);
                current = current.toTheRight();
            } else if (current.below().canBeMovedTo()) {

                path.push(current);
                current.place(Maze.Cell.PATH);
                current = current.below();
            } else if (current.toTheLeft().canBeMovedTo()) {

                path.push(current);
                current.place(Maze.Cell.PATH);
                current = current.toTheLeft();
            } else {

                current.place(Maze.Cell.TRIED);
                if(path.empty()){
                  return false;
                }
                path.pop();

            }
        }
    }
}
