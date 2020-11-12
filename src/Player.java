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
}
