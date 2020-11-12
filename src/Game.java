import java.util.ArrayList;
import java.util.Collections;

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

    public void runRound(int activePlayer, int highestBid, ArrayList<Player> folded){
        while(true) {
            for (int i = 0; i < this.players.size(); i++) {
                highestBid = this.choose(activePlayer+i, highestBid, folded);
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

    public boolean foldCheck(ArrayList<Player> folded){
        if (folded.size() == this.players.size()-1){
            for (int i = 0; i < this.players.size(); i++) {
                if(!folded.contains(this.players.get(i))){
                    this.players.get(i).transfer(-this.moneyPile);
                    return true;
                }
            }
        }
        return false;
    }

    public void runGame(int highestBid){
        int activePlayer = 0;
        this.allDraw(3);
        this.players.get(activePlayer).setMoneyBuffer(highestBid/2);
        activePlayer++;
        this.players.get(activePlayer).setMoneyBuffer(highestBid);
        activePlayer++;
        ArrayList<Player> folded =new ArrayList<>();
        this.runRound(activePlayer, highestBid, folded);
        if(this.foldCheck(folded)){
            return;
        }
        this.middlePile.open();
        for (int i = 0; i < this.players.size(); i++) {
            this.players.get((i+activePlayer)%this.players.size()).discard(this.chooseDiscard((i+activePlayer)%this.players.size()));
        }
        this.runRound(activePlayer, 0, folded);
        if(this.foldCheck(folded)){
            return;
        }
        this.middlePile.open();
        this.runRound(activePlayer, 0, folded);
        if(this.foldCheck(folded)){
            return;
        }
        this.middlePile.open();
        this.runRound(activePlayer, 0, folded);
        if(this.foldCheck(folded)){
            return;
        }
        this.finishGame(folded);


    }

    public void call(int highestBid, int playerIndex){
        this.players.get(playerIndex).transfer(highestBid - this.players.get(playerIndex).getMoneyBuffer());
    }

    public void raise(int desiredAmount, int playerIndex){
        this.players.get(playerIndex).transfer(desiredAmount - this.players.get(playerIndex).getMoneyBuffer());
    }

    public void fold(int playerIndex, ArrayList<Player> folded){
        this.players.get(playerIndex).setMoneyBuffer(0);
        folded.add(this.players.get(playerIndex));
    }

    public int choose(int playerIndex, int highestBid, ArrayList<Player> folded){
        if (!folded.contains(this.players.get(playerIndex))) {


            //choice of action requires gui
        }
        return 1;
    }
    public int chooseDiscard(int playerIndex){
        //choice of discard requires gui
        return 1;
    }

    public void finishGame(ArrayList<Player> folded){
        ArrayList<int[]> scores = new ArrayList<>(players.size());
        for (int i = 0; i < players.size(); i++) {
            if (!folded.contains(players.get(i))){
                ArrayList<Card> cards = new ArrayList<>(7);
                cards.addAll(players.get(i).getHand());
                cards.addAll(middlePile.get());
                scores.add(finalScore.standartScoring.score(cards));
            }
        }
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getHand());
        }
        System.out.println(this.middlePile.get());
        for (int[] score: scores){
            for (int i = 0; i < 3;  i++) {
                System.out.print(score[i] + " ");
            }
            System.out.println("");
        }
        ArrayList<Integer> first = new ArrayList<>(players.size());
        ArrayList<Integer> second = new ArrayList<>(players.size());
        ArrayList<Integer> third = new ArrayList<>(players.size());
        for (int[] score: scores){
            first.add(score[0]);
        }
        int firstMax = Collections.max(first);
        ArrayList<Integer> firstIndexMax = new ArrayList<>(first.size());
        for (int i = 0; i < first.size(); i++) {
            if (first.get(i) == firstMax){
                firstIndexMax.add(i);
            }
        }
        if(firstIndexMax.size() == 1){
            players.get(firstIndexMax.get(0)).transfer(-moneyPile);
        }
        else{
        for (int i = 0; i < players.size(); i++) {
            if(firstIndexMax.contains(i)){
                second.add(scores.get(i)[1]);
            }
        }
        ArrayList<Integer> secondIndexMax = new ArrayList<>(first.size());
        int secondMax = Collections.max(second);
        for (int i = 0; i < second.size(); i++) {
            if (second.get(i) == secondMax){
                secondIndexMax.add(i);
            }
        }
        if(secondIndexMax.size() == 1){
            players.get(secondIndexMax.get(0)).transfer(-moneyPile);
        }
        else{
        for (int i = 0; i < players.size(); i++) {
            if(secondIndexMax.contains(i)){
                third.add(scores.get(i)[1]);
            }
        }
        ArrayList<Integer> thirdIndexMax = new ArrayList<>(first.size());
        int thirdMax = Collections.max(second);
        for (int i = 0; i < third.size(); i++) {
            if (third.get(i) == thirdMax){
                thirdIndexMax.add(i);
            }
        }
        if(thirdIndexMax.size() == 1) {
            players.get(thirdIndexMax.get(0)).transfer(-moneyPile);
        }
        else{
            for (int i = 0; i < thirdIndexMax.size(); i++) {
                players.get(thirdIndexMax.get(i)).transfer(-moneyPile/thirdIndexMax.size());
            }
        }
        }
        }
    }
}
