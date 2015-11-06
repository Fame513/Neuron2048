package game;

/**
 * Created by fame on 04.11.15.
 */
public class Board {
    private Chip[][] fild;
    private int width;
    private int height;

    public Board(int x, int y) throws Exception{
        if (x < 1 || y < 1)
            throw new Exception("Wrong input data");
        width = x;
        height = y;
        this.fild = new Chip[x][y];
    }

    public void addChip(int x, int y, int value) throws Exception{
        if (fild[x][y] != null)
            throw new Exception("Not empty");
        fild[x][y] = new Chip(value);
    }

    private void removeChip(int x, int y) throws RuntimeException{
        fild[x][y] = null;
    }

    public Chip[][] getFild() {
        return fild;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
            for (int y = 0; y < height; y++){
                for (int x = 0; x < width; x++){

                    result.append(fild[x][y] == null ? '_' : fild[x][y]);
                    result.append(' ');
                }
                result.append('\n');
            }
        return new String(result);
    }

    private void moveChip(int fromX, int fromY, int toX, int toY){
        if (fromX != toX || fromY != toY){
            fild[toX][toY] = fild[fromX][fromY];
            removeChip(fromX, fromY);
        }
    }

    private void moveRowLeft(int row){
        for(int from = 0, to = 0; from < width; from++){
            if (fild[from][row] != null){
                moveChip(from, row, to++, row);
            }
        }
    }

    private void moveRowRight(int row){
        for(int from = width-1, to = width-1; from >= 0; from--){
            if (fild[from][row] != null){
                moveChip(from, row, to--, row);
            }
        }
    }

    private void moveColumeDown(int colume){
        for(int from = height-1, to = height-1; from >= 0; from--){
            if (fild[colume][from] != null){
                moveChip(colume, from, colume, to--);
            }
        }

    }

    private void moveColumeUp(int colume){
        for(int from = 0, to = 0; from < height; from++){
            if (fild[colume][from] != null){
                moveChip(colume, from, colume, to++);
            }
        }
    }

    private int checkRowLeft(int row){
        int score = 0;
        for (int i = 0; i < width - 1; i++){
            if (fild[i+1][row] == null)
                return score;
            if (fild[i][row].getValue() == fild[i+1][row].getValue()){
                fild[i][row].up();
                removeChip(i+1, row);
                score += fild[i][row].getValue();
                moveRowLeft(row);
            }
        }
        return score;
    }

    private int checkRowRight(int row){
        int score = 0;
        for (int i = width-1; i > 0; i--){
            if (fild[i-1][row] == null)
                return score;
            if (fild[i][row].getValue() == fild[i-1][row].getValue()){
                fild[i][row].up();
                removeChip(i-1, row);
                score += fild[i][row].getLogValue();
                moveRowRight(row);
            }
        }
        return score;
    }

    private int checkColumeUp(int colume){
        int score = 0;
        for (int i = 0; i < height - 1; i++){
            if (fild[colume][i+1] == null)
                return score;
            if (fild[colume][i].getValue() == fild[colume][i+1].getValue()){
                fild[colume][i].up();
                removeChip(colume, i+1);
                score += fild[colume][i].getValue();
                moveColumeUp(colume);
            }
        }
        return score;
    }

    private int checkColumeDown(int colume){
        int score = 0;
        for (int i = height-1; i > 0; i--){
            if (fild[colume][i-1] == null)
                return score;
            if (fild[colume][i].getValue() == fild[colume][i-1].getValue()){
                fild[colume][i].up();
                removeChip(colume, i-1);
                score += fild[colume][i].getValue();
                moveColumeDown(colume);
            }
        }
        return score;
    }

    public int moveUp(){
        int score = 0;
        for (int colume = 0; colume < width; colume++){
            moveColumeUp(colume);
            score += checkColumeUp(colume);
        }
        return score;
    }

    public int moveDown(){
        int score = 0;
        for (int colume = 0; colume < width; colume++){
            moveColumeDown(colume);
            score += checkColumeDown(colume);
        }
        return score;
    }

    public int moveLeft(){
        int score = 0;
        for (int row = 0; row < height; row++){
            moveRowLeft(row);
            score += checkRowLeft(row);
        }
        return score;
    }

    public int moveRight(){
        int score = 0;
        for (int row = 0; row < height; row++){
            moveRowRight(row);
            score += checkRowRight(row);
        }
        return score;
    }

    public static void main(String[] args) {
        try {
            Board desck = new Board(5, 4);
            desck.addChip(2, 2, 4);
            desck.addChip(3, 2, 4);
            desck.addChip(2, 3, 4);
            desck.addChip(3, 3, 4);

            System.out.println(desck);
            desck.moveDown();
            desck.moveRight();
//            desck.moveColumeDown(0);
            System.out.println(desck);
        } catch (Exception e){
            System.err.println(e);
        }

    }
}
