import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private Deck drawPile;
    private Deck discardPile;
    private Integer moneyPile;
    private int moneyBuffer;
    private int money;

    public Player(Deck drawPile, Deck discardPile, int money, Integer moneyPile){
        this.hand = new ArrayList<>();
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        this.money = money;
        this.moneyPile = moneyPile;
        this.moneyBuffer = 0;
    }

    public void draw(int amount){
        for(int i=0; i<amount; i++){
            this.hand.add(this.drawPile.draw());
        }
    }

    public void discard(int index){
        this.discardPile.add(this.hand.get(index));
        this.hand.remove(index);
    }

    public void transfer(int amount){
        this.moneyPile += amount;
        this.money -= amount;
        this.incMoneyBuffer(amount);
    }

    public int getMoneyBuffer(){
        return this.moneyBuffer;
    }
    public void setMoneyBuffer(int moneyBuffer){
        this.moneyBuffer = moneyBuffer;
    }
    public void incMoneyBuffer(int amount){
        this.moneyBuffer += amount;
    }
    public ArrayList<Card> getHand(){return this.hand;}

    public void paintHand(Group root, int x, int y, boolean rotate, boolean open){
        if(!rotate) {
            x -= this.hand.size() * 50;
            for (int i = 0; i < this.hand.size(); x+=99, i++) {
                this.hand.get(i).paintCard(root, x, y, rotate, open);
            }
        }
        else{
            y -= this.hand.size() * 50;
            for (int i = 0; i < this.hand.size(); y+=99, i++) {
                this.hand.get(i).paintCard(root, x, y, rotate, open);
            }
        }


    }

    public void paintMoney(Group root, int x, int y){
        Text money = new Text(x, y, Integer.toString(this.money));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.getChildren().add(money);
            }
        });
    }

    public void paint(Group root, int x, int y, boolean rotate, boolean open){
        this.paintHand(root, x, y, rotate, open);
        if (!rotate) {
            this.paintMoney(root, x + this.hand.size() * 50 + 10, y + 75);
        }
        else {
            this.paintMoney(root, x+75, y+this.hand.size()*50+10);
        }
    }
}
