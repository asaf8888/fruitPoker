import javafx.scene.Group;
import javafx.stage.Stage;

public class PaintParams {
    private Group root;
    private Stage stage;
    private int x;
    private int y;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public PaintParams(Group root, Stage stage, int x, int y) {
        this.root = root;
        this.stage = stage;
        this.x = x;
        this.y = y;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
