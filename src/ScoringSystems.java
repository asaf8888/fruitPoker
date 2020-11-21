import java.util.ArrayList;
import java.util.Collections;

public class ScoringSystems {


    public static  winCondition highestCard = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            return Collections.max(cards).getNum();
        }
    };

    public static winCondition straightFlush = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            int score = 0;
            ArrayList<Card> sortedCopy = new ArrayList<Card>(cards);
            Collections.sort(sortedCopy);
            for (int i = 0; i < cards.size()-4; i++) {
                Card min =sortedCopy.get(i);
                for (Card card = new Card(min.getNum(), min.getType()); card.getNum() < min.getNum() + 5; card.setNum(card.getNum() + 1)) {
                    if (!cards.contains(card)) {
                        break;
                    }
                    if (card.getNum() == min.getNum() + 4 && score<Collections.max(cards).getNum()) {
                            score = Collections.max(cards).getNum();
                    }
                }
            }
                return score;
        }
    };

    public static winCondition fourOfAKind = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            int score = 0;
            ArrayList<Integer> cardNums =new ArrayList<>(cards.size());
            for (int i = 0; i < cards.size(); i++) {
                cardNums.add(cards.get(i).getNum());
            }
            for (int i = 0; i < cards.size()-3; i++) {
                ArrayList<Integer> fourOfAKind = new ArrayList<>(4);
                for (int j = 0; j < 4 ; j++) {
                    fourOfAKind.add(cardNums.get(i));
                }
                if(containsAllDuplicates(cardNums, fourOfAKind) && score<i){
                        score = i;
                }
            }
            return score;
        }
    };

    public static  winCondition fullHouse = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            int score = 0;
            ArrayList<Integer> cardNums =new ArrayList<>(cards.size());
            for (int i = 0; i < cards.size(); i++) {
                cardNums.add(cards.get(i).getNum());
            }
            for (int i = 0; i < cards.size()-2; i++) {
                ArrayList<Integer> threeOfAKind = new ArrayList<>(3);
                for (int j = 0; j < 3; j++) {
                    threeOfAKind.add(cardNums.get(i));
                }
                if(containsAllDuplicates(cardNums, threeOfAKind)){
                    ArrayList<Integer> pair = new ArrayList<>(2);
                    pair.add(0);
                    pair.add(0);
                    for (int j = 0; j < cards.size()-1; j++) {
                        if(!cardNums.get(j).equals(cardNums.get(i))){
                            pair.set(0, cardNums.get(j));
                            pair.set(1, cardNums.get(j));
                            if(containsAllDuplicates(cardNums, pair) && score<cardNums.get(i)*14+cardNums.get(j)){
                                    score = cardNums.get(i)*14+cardNums.get(j);
                            }
                        }
                    }
                }
            }
            return score;
        }
    };

    public static winCondition flush = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            int score = 0;
            ArrayList<Card.Type> cardTypes = new ArrayList<>(cards.size());
            for (int i = 0; i < cards.size(); i++) {
                cardTypes.add(cards.get(i).getType());
            }
            for (int i = 0; i < cards.size()-4; i++) {
                ArrayList<Card.Type> flush = new ArrayList<>(5);
                for (int j = 0; j < 5; j++) {
                    flush.add(cards.get(i).getType());
                }
                if(containsAllDuplicates(cardTypes, flush)){
                    ArrayList<Card> typeSpecific = new ArrayList<>(7);
                    for (int j = 0; j < 7; j++) {
                        if(cards.get(j).getType() == cardTypes.get(0)){
                            typeSpecific.add(cards.get(j));
                        }
                    }
                    score = Collections.max(typeSpecific).getNum();
                }
            }
            return score;
        }
    };

    public static winCondition straight = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            int score = 0;
            ArrayList<Integer> sortedInts = new ArrayList<>(cards.size());
            for (int i = 0; i < cards.size(); i++) {
                sortedInts.add(cards.get(i).getNum());
            }
            Collections.sort(sortedInts);
            for (int i = 0; i < cards.size()-4; i++) {
                for (int j = sortedInts.get(i); j < 5 + sortedInts.get(i); j++) {
                    if(!sortedInts.contains(j)){
                        break;
                    }
                    if(j == sortedInts.get(i)+4 && score<Collections.max(sortedInts)){
                        score = Collections.max(sortedInts);
                    }
                }
            }
            return score;
        }
    };

    public static winCondition threeOfAKind = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            int score = 0;
            ArrayList<Integer> cardNums =new ArrayList<>(cards.size());
            for (int i = 0; i < cards.size(); i++) {
                cardNums.add(cards.get(i).getNum());
            }
            for (int i = 0; i < cards.size()-2; i++) {
                ArrayList<Integer> threeOfAKind = new ArrayList<>(3);
                for (int j = 0; j < 3; j++) {
                    threeOfAKind.add(cardNums.get(i));
                }
                if (containsAllDuplicates(cardNums, threeOfAKind) && score < threeOfAKind.get(0)) {
                    score = threeOfAKind.get(0);
                }
            }
            return score;
        }
    };

    public static winCondition twoPair = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            int score = 0;
            ArrayList<Integer> cardNums =new ArrayList<>(cards.size());
            for (int i = 0; i < cards.size(); i++) {
                cardNums.add(cards.get(i).getNum());
            }
            ArrayList<Integer> pair = new ArrayList<>(2);
            pair.add(0);
            pair.add(0);
            for (int i = 0; i < cards.size()-1; i++) {
                pair.set(0, cardNums.get(i));
                pair.set(1, cardNums.get(i));
                if(containsAllDuplicates(cardNums, pair)){
                    for (int j = 0; j < cards.size()-1; j++) {
                        if(!cardNums.get(j).equals(cardNums.get(i))){
                            pair.set(0, cardNums.get(j));
                            pair.set(1, cardNums.get(j));
                            if(containsAllDuplicates(cardNums, pair) && score < Math.max(cardNums.get(i), cardNums.get(j)) * 14 + Math.min(cardNums.get(i), cardNums.get(j)) ){
                                score = Math.max(cardNums.get(i), cardNums.get(j)) * 14 + Math.min(cardNums.get(i), cardNums.get(j));
                            }
                        }
                    }
                }
            }
        return score;
        }
    };

    public static winCondition pair = new winCondition() {
        @Override
        public int condition(ArrayList<Card> cards) {
            int score = 0;
            ArrayList<Integer> cardNums = new ArrayList<>(cards.size());
            for (int i = 0; i < cards.size(); i++) {
                cardNums.add(cards.get(i).getNum());
            }
            ArrayList<Integer> pair = new ArrayList<>(2);
            pair.add(0);
            pair.add(0);
            for (int i = 0; i < cards.size()-1; i++) {
                pair.set(0, cardNums.get(i));
                pair.set(1, cardNums.get(i));
                if (containsAllDuplicates(cardNums, pair) && score < cardNums.get(i)) {
                    score = cardNums.get(i);
                }
            }
            return score;
        }
    };

    public static <T> boolean containsAllDuplicates(ArrayList<T> container, ArrayList<T> all){
        ArrayList<T> clone = new ArrayList<>(container);
        for (T item: all) {
            if(!clone.contains(item)){
                return false;
            }
            else{
                clone.remove(item);
            }
        }
        return true;
    }
}
