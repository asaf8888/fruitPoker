import  java.util.ArrayList;
public class Deck {
    private ArrayList<Card> deck;
    public static final ArrayList<Card> standard = new ArrayList<>();
    static {
        for(Card.Type type: Card.Type.values()){
            for(int i = 1; i <= 13; i++){
                standard.add(new Card(i, type));
            }
        }
    }

    public Deck(ArrayList<Card> list){
        this.deck = list;
    }

    public Deck(){
        this.deck = standard;
        this.shuffle();
    }


    public void shuffle(){
        ArrayList<Card> newDeck = new ArrayList<>();
        for (int i = 0; i<this.deck.size(); i++){
            int index = (int) ((Math.random() * this.deck.size()+1));
            newDeck.add(this.deck.get(index));
            this.deck.remove(index);

        }
        this.deck = newDeck;
    }

    public Card draw(){
        Card result = this.deck.get(this.deck.size()-1);
        this.deck.remove(this.deck.size()-1);
        return result;
    }

    public void add(Card newCard){
        this.deck.add(newCard);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<this.deck.size(); i++){
            result.append(this.deck.subList(i, i + 1).toString()).append("\n");
        }
        return result.toString();
    }
}
