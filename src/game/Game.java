package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {
    static int score = 0;
    enum direct {UP, DOWN, LEFT, RIGHT}

    public static void main(String[] args) throws Exception{
        Board board = new Board(4, 5);
        Random rand = new Random();

        while (true) {
            boolean added = true;
            do {
                try {
                    board.addChip(rand.nextInt(board.getWidth()), rand.nextInt(board.getHeight()), 2);
                    added = true;
                } catch (Exception e){
                    added = false;
                }
            } while (!added);
            System.out.println(score);
            System.out.println(board);
            switch (moveRead()) {
                case UP:
                    score += board.moveUp();
                    break;
                case DOWN:
                    score += board.moveDown();
                    break;
                case LEFT:
                    score += board.moveLeft();
                    break;
                case RIGHT:
                    score += board.moveRight();
                    break;
            }
        }
    }

    public static direct moveRead() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value;
        while (true){
            value = br.readLine();
            if (value.equalsIgnoreCase("up")){
                return direct.UP;
            } else if (value.equalsIgnoreCase("down")){
                return direct.DOWN;
            } else if (value.equalsIgnoreCase("left")){
                return direct.LEFT;
            } else if (value.equalsIgnoreCase("right")){
                return direct.RIGHT;
            } else if (value.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
        }
    }
}
