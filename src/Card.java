public class Card {
     enum Type{
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    private int num;
    private Type type;

    public Card(int num, Type type){
        this.num = num;
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return (Integer.toString(this.num) + " of " + this.type.name());
    }
}
