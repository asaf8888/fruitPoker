import javafx.application.Platform;

public class Main {

    public static void main(String[] args) {
        System.out.println(Platform.isFxApplicationThread());
    }
}
