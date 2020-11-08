public class Main {

    public static void main(String[] args) {
        Deck test = new Deck(Deck.standard);
        Player tester = new Player(test);
        tester.draw(1);
        System.out.println(test);
    }
}
