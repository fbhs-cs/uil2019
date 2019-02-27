import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

//change the class name
public class pegs
{

    public int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    boolean output;

    public void run() throws Exception
    {
        Scanner file = new Scanner(new File("pegs.dat"));

        //read in the number at the top of the data file
        int times = file.nextInt();
        //pick up the left over enter key
        file.nextLine();

        //read in each data set
        for(int am = 0; am < times; am++)
        {
            int r = file.nextInt();
            int c = file.nextInt();
            file.nextLine();
            char[][] board = new char[r][c];
            output = false;
            for(int j = 0; j < r; j++){
                board[j] = String.format("%-" + r + "s", file.nextLine()).replaceAll(" ", "#").toCharArray();
            }
            recur(board);

            System.out.println(output ? "Solvable!" : "Impossible.");

        }
    }

    public void recur(char[][] board){
        ArrayList<Integer[]> moves = findMoves(board);
        if(moves.size() == 0){
            if(oneLeft(board)) output = true;
            return;
        }
        for(int i = 0; i < moves.size(); i++){
            char [][] temp = new char[board.length][board[0].length];
            for(int j = 0; j < board.length; j++)
                for(int k = 0; k < board[0].length; k++)
                    temp[j][k] = board[j][k];
            Integer[] move = moves.get(i);
            int r = move[0];
            int c = move[1];
            int dir = move[2];
            temp[r][c] = '.';
            temp[r + dirs[dir][0]][c + dirs[dir][1]] = '.';
            temp[r + dirs[dir][0] * 2][c + dirs[dir][1] * 2] = '@';
            recur(temp);
        }
    }

    public ArrayList<Integer[]> findMoves(char[][] board){
        ArrayList<Integer[]> moves = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '@'){
                    //up
                    if(i > 1 && board[i-1][j] == '@' && board[i-2][j] == '.')
                        moves.add(new Integer[]{i, j, 0});
                    //right
                    if(j < board[0].length - 2 && board[i][j+1] == '@' && board[i][j+2] == '.')
                        moves.add(new Integer[]{i, j, 1});
                    //down
                    if(i < board.length - 2 && board[i+1][j] == '@' && board[i+2][j] == '.')
                        moves.add(new Integer[]{i, j, 2});
                    //left
                    if(j > 1 && board[i][j-1] == '@' && board[i][j-2] == '.')
                        moves.add(new Integer[]{i, j, 3});
                }
            }
        }
        return moves;
    }

    public boolean oneLeft(char[][] board){
        int pegs = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '@')
                    pegs++;
            }
        }
        return pegs == 1;
    }

    public static void main(String[] args) throws Exception
    {
        //change this to whatever your class name is
        new pegs().run();
    }

}