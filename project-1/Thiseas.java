import java.io.*;
import java.util.NoSuchElementException;

public class Thiseas {
    public static void readFile(String fileName) {

        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        try {
            line = reader.readLine();
            String[] dim = line.split(" ");
            int rows = Integer.parseInt(dim[0]);
            int col = Integer.parseInt(dim[1]);
            line = reader.readLine();
            String[] input = line.split(" ");
            int inputRow = Integer.parseInt(input[0]);
            int inputCol = Integer.parseInt(input[1]);

            line = reader.readLine();
            boolean exists = false;

            //We store the elements of the file in an array
            String[][] tempMaze = new String[rows][col];
            for (int i = 0; i < rows; i++) {
                if (line == null){
                    System.out.println("Wrong, there are less rows than it is referred");
                    return;
                }
                String[] temp = line.split(" ");
                if (temp.length > col) {
                    System.out.println("Wrong, there are more columns than it is referred");
                    return;
                } else if (temp.length < col){
                    System.out.println("Wrong, there are less columns than it is referred");
                    return;
                }
                for (int j = 0; j < col; j++) {
                    tempMaze[i][j] = temp[j];
                    if (temp[j].equals("E")) {
                        exists = true;
                    }
                }
                line = reader.readLine();
            }

            if (line != null) {
                System.out.println("Wrong, there are more rows than it is referred");
                return;
            }


            if (!exists) {
                System.out.println("Wrong, no entry found");
                return;
            }

            if (!tempMaze[inputRow][inputCol].equals("E")) {
                System.out.println("Wrong position of entry");
                return;
            }

            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Error closing file");
            }



            Maze(tempMaze, inputRow, inputCol, rows, col);//if there is no wrong entry, start searching an exit


        } catch (IOException e) {
            System.out.println(("Error reading line"));
        }
    }
    //if there is an exit, find it's coordinates
    public static void Maze(String[][] tempMaze, int row, int col, int numOfRows, int numOfCols) {

        StringStack coords = new StringStackImpl(); //stack that stores the coordinates
        StringStack zeros =  new StringStackImpl(); //stack that stores zeros next to an element

        System.out.println("E= " + row + "," + col);

        //find the coordinates of the next zero
        if (row == 0)
            row++;
        else if ( row == (numOfRows-1))
            row--;
        else if (col == 0)
            col++;
        else
            col--;


        if (Integer.parseInt(tempMaze[row][col]) == 1) {
            System.out.println(("There is no path to the exit"));
            return;
        }



        int initialrow=row;
        int initialcol=col;

        String tmp;
        boolean done = false;
        String lastmove="";


        try {

            while (!done) {

                boolean move = false;

                if (((row > 0) && (row < numOfRows - 1)) && ((col > 0) && (col < numOfCols - 1))) {


                    while (tempMaze[row][col + 1].equals("0") && !lastmove.equals("left")) { //goes right

                        move = true;
                        col++;
                        lastmove = "right";
                        coords.push(Integer.toString(row) + Integer.toString(col));

                        int j; //number of zeros next to the element
                        if (((row > 0) && (row < numOfRows - 1)) && ((col > 0) && (col < numOfCols - 1))) {
                            j = Counter(tempMaze, row, col);
                        } else {
                            break;
                        }

                        zeros.push(Integer.toString(j));

                        //if you reach at a dead-end, convert the elements that lead there, to 1
                        if (j == 1 && !tempMaze[row][col - 1].equals("E") && coords.size() != 0) {
                            while (!zeros.isEmpty() && (zeros.peek().equals("1") || zeros.peek().equals("2"))) {
                                tmp = coords.peek();
                                row = Integer.parseInt(tmp.substring(0, 1));
                                col = Integer.parseInt(tmp.substring(1));
                                tempMaze[row][col] = Integer.toString(1);
                                zeros.pop();
                                coords.pop();
                            }

                            //creates coordinates from the previous element
                            if (!coords.isEmpty()) {

                                tmp = coords.peek();
                                row = Integer.parseInt(tmp.substring(0, 1));
                                col = Integer.parseInt(tmp.substring(1));
                            } else {
                                row = initialrow;
                                col = initialcol;
                            }
                        }

                    }

                    while (tempMaze[row][col - 1].equals("0") && !lastmove.equals("right")) { //goes left

                        move = true;
                        col--;
                        lastmove = "left";
                        coords.push(Integer.toString(row) + Integer.toString(col));

                        int j;
                        if (((row > 0) && (row < numOfRows - 1)) && ((col > 0) && (col < numOfCols - 1))) {
                            j = Counter(tempMaze, row, col);
                        } else {
                            break;
                        }

                        zeros.push(Integer.toString(j));

                        //dead-end
                        if (j == 1 && !tempMaze[row][col + 1].equals("E") && coords.size() != 0) {
                            while (coords.size() != 0 && (zeros.peek().equals("1") || zeros.peek().equals("2"))) {
                                tmp = coords.peek();
                                row = Integer.parseInt(tmp.substring(0, 1));
                                col = Integer.parseInt(tmp.substring(1));
                                tempMaze[row][col] = Integer.toString(1);
                                zeros.pop();
                                coords.pop();
                            }
                            if (coords.size() != 0) {
                                tmp = coords.peek();
                                row = Integer.parseInt(tmp.substring(0, 1));
                                col = Integer.parseInt(tmp.substring(1));
                            } else {
                                row = initialrow;
                                col = initialcol;
                            }


                        }

                    }

                    while (tempMaze[row + 1][col].equals("0") && !lastmove.equals("up")) { //goes down
                        row++;
                        lastmove = "down";
                        coords.push(Integer.toString(row) + Integer.toString(col));

                        move = true;


                        int j;
                        if (((row > 0) && (row < numOfRows - 1)) && ((col > 0) && (col < numOfCols - 1))) {
                            j = Counter(tempMaze, row, col);
                        } else {
                            break;
                        }

                        zeros.push(Integer.toString(j));

                        //dead-end
                        if (j == 1 && !tempMaze[row + 1][col].equals("E") && coords.size() != 0) {
                            while (coords.size() != 0 && (zeros.peek().equals("1") || zeros.peek().equals("2"))) {
                                tmp = coords.peek();
                                row = Integer.parseInt(tmp.substring(0, 1));
                                col = Integer.parseInt(tmp.substring(1));
                                tempMaze[row][col] = Integer.toString(1);
                                zeros.pop();
                                coords.pop();
                            }

                            if (coords.size() != 0) {
                                tmp = coords.peek();
                                row = Integer.parseInt(tmp.substring(0, 1));
                                col = Integer.parseInt(tmp.substring(1));
                            } else {
                                row = initialrow;
                                col = initialcol;
                            }
                        }
                    }

                    while ((tempMaze[row - 1][col].equals("0")) && !lastmove.equals("down")) { //goes up
                        row--;
                        lastmove = "up";
                        coords.push(Integer.toString(row) + Integer.toString(col));

                        move = true;

                        int j;
                        if (((row > 0) && (row < numOfRows - 1)) && ((col > 0) && (col < numOfCols - 1))) {
                            j = Counter(tempMaze, row, col);
                        } else {
                            break;
                        }
                        zeros.push(Integer.toString(j));

                        //dead-end
                        if (j == 1 && !tempMaze[row - 1][col].equals("E") && coords.size() != 0) {
                            while (zeros.size() != 0 && (zeros.peek().equals("1") || zeros.peek().equals("2"))) {
                                tmp = coords.peek();
                                row = Integer.parseInt(tmp.substring(0, 1));
                                col = Integer.parseInt(tmp.substring(1));
                                tempMaze[row][col] = Integer.toString(1);
                                zeros.pop();
                                coords.pop();
                            }

                            if (coords.size() != 0) {
                                tmp = coords.peek();
                                row = Integer.parseInt(tmp.substring(0, 1));
                                col = Integer.parseInt(tmp.substring(1));
                            } else {
                                row = initialrow;
                                col = initialcol;
                            }
                        }

                    }

                    if (!move) {
                        System.out.println("Could not reach the exit"); //there is no position left to go or there is no exit at all
                        break;
                    }


                } else {
                    done = true;
                    System.out.println("The coordinates of the exit are: (" + row + "," + col + ")");

                }

            }
            if (row == initialrow && col == initialcol) {
                lastmove = "";
            }
        } catch (NoSuchElementException e) {
            System.err.println("Tried to removed from an empty stack!");
        }
    }

    //counts zeros
    public static int Counter(String[][] tempMaze, int row, int col) {
        int j = 0;
        if (tempMaze[row][col + 1].equals("0"))
            j++;
        if (tempMaze[row][col - 1].equals("0"))
            j++;
        if ((tempMaze[row + 1][col].equals("0")))
            j++;
        if ((tempMaze[row - 1][col].equals("0")))
            j++;
        return j;
    }


    public static void main (String[] args){
            readFile(args[0]);
    }
}