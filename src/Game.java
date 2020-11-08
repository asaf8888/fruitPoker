import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Deck drawPile;
    private Deck discardPile;
    private Integer moneyPile;
    private MiddlePile middlePile;

    public Game(int playerNum, int moneyStart){
        this.drawPile = new Deck();
        this.discardPile = new Deck(new ArrayList<Card>());
        this.moneyPile = 0;
        this.middlePile = new MiddlePile(this.drawPile);
        this.players = new ArrayList<>();
        for(int i = 0; i<playerNum; i++){
            this.players.add(new Player(drawPile, discardPile, moneyStart, moneyPile));
        }
    }

    public void allDraw(int amount){
        for(int i = 0; i<this.players.size(); i++){
            this.players.get(i).draw(amount);
        }
    }

    public void runGame(int moneyStart){
        int activePlayer = 0;
        this.allDraw(3);
        int highestBid = moneyStart/20;
        while(true) {
            for (int i = 0; i < this.players.size(); i++) {

            }
            s:
            {
                for(Player player: this.players) {
                    if(player.getMoneyBuffer()<highestBid && player.getMoneyBuffer() != 0){
                        break s;
                    }
                }
            break;
            }
        }
    }

    public void call(int highestBid, int playerIndex){
        this.players.get(playerIndex).transfer(highestBid - this.players.get(playerIndex).getMoneyBuffer());
    }

    public void raise(int desiredAmount, int playerIndex){
        this.players.get(playerIndex).transfer(desiredAmount - this.players.get(playerIndex).getMoneyBuffer());
    }

    public void fold(int playerIndex){
        this.players.get(playerIndex).setMoneyBuffer(0);
    }
}
