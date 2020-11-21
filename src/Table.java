import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Table {
    Sit up;
    Sit down;
    Sit right;
    Sit left;

    public Table(ArrayList<Player> players, int width, int hight) {
        Player[] up = new Player[3];
        Player[] down = new Player[1];
        Player[] right = new Player[2];
        Player[] left = new Player[2];
        for (int i = 0; i < players.size(); i++) {
            if((i) == 0){
                down[i] = players.get(i);
            }
            if((i)%3 == 1 ){
                up[i/3] = players.get(i);
            }
            if((i-1)%3 == 1){
                right[i/3] = players.get(i);
            }
            if((i-2)%3 == 1){
                left[i/3-1] = players.get(i);
            }

        }
        this.down = new Sit(width, hight, Sit.Side.DOWN, down);
        this.up = new Sit(width, hight, Sit.Side.UP, up);
        this.right = new Sit(width, hight, Sit.Side.RIGHT, right);
        this.left = new Sit(width, hight, Sit.Side.LEFT, left);
    }

    public void paintTable(Group root){
        this.down.paint(root);
        this.up.paint(root);
        this.right.paint(root);
        this.left.paint(root);
    }




    private static class Sit{
        private int width;
        private int hight;
        private ArrayList<Player> players;
        private Side side;
        private enum Side{
            RIGHT, LEFT, UP, DOWN
        }
        private Sit(int width, int hight, Side side, Player[] players){
            this.width = width;
            this.hight = hight;
            this.players = new ArrayList<>(Arrays.asList(players));
            while (this.players.remove(null));
            this.side =side;
        }

        private void paint(Group root){
            if (side == Side.UP){
                int section = width/players.size();
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).paint(root, section*(i+1)-section/2, 0, false, false);
                }
            }
            else if (side == Side.DOWN){
                players.get(0).paint(root, width/2, hight-150, false, true);
            }
            else if (side == Side.RIGHT){
                int section = hight/players.size();
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).paint(root, width-150, section*(i+1)-section/2, true, false);
                }
            }
            else if (side == Side.LEFT){
                int section = hight/players.size();
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).paint(root, 0, section*(i+1)-section/2, true, false);
                }
            }
        }
    }
}
