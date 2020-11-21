import javafx.scene.Group;

import java.util.ArrayList;

public class MiddlePile {
    private ArrayList<Card> middle;
    private Deck drawPile;

    public MiddlePile(Deck drawPile){
        this.middle = new ArrayList<>();
        this.drawPile = drawPile;
    }

    public ArrayList<Card> get(){
        return this.middle;
    }

    private void draw(int amount){
        for(int i=0; i<amount; i++){
            this.middle.add(this.drawPile.draw());
        }
    }

    public void open(){
        if(this.middle.size() == 0){
            this.draw(3);
        }
        if(this.middle.size() == 3){
            this.draw(1);
        }
        if(this.middle.size() == 4){
            this.draw(1);
        }
    }

    public void paint(Group root, int x, int y){
        x -= this.get().size()*75;
        for (int i = 0; i < this.get().size(); x+=150, i++) {
            this.get().get(i).paintCard(root, x, y, false, true);
        }
    }

}
