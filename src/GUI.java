import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class GUI extends Application{


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("pineapple");
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        Game game = new Game(8, 1000);
        Button start = new Button("start");
        root.getChildren().add(start);
        stage.show();
        Thread back = new Thread(new Runnable() {
            @Override
            public void run() {
                game.runGame(50, new PaintParams(root, stage, (int)scene.getWidth(), (int)scene.getHeight()));
            }
        });
        start.setOnAction(actionEvent -> {
            root.getChildren().remove(start);
            back.start();
        });


    }
    public static void main(String[] args) {
        launch(args);
    }



}
