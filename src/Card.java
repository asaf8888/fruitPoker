import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import java.io.File;

public class Card implements Comparable<Card>{

    @Override
    public int compareTo(Card card) {
        if(card.getNum() == 1){
            card.setNum(14);
        }

        if(this.num == 1){
            this.num = 14;
        }
        if(card.getNum() < this.num){
            return 1;
        }
        if(card.getNum() == this.num){
            return 0;
        }
        return -1;
    }

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

    public void paintCard(Group root, int x, int y, boolean rotate, boolean open){
        String path;
        if(open) {
             path = "res/JPEG/" + this.num + Character.toUpperCase(this.getType().toString().charAt(0)) + ".jpg";
        }
        else {
             path = "res/JPEG/Red_back.jpg";
        }
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        ImageView iv1 = new ImageView(image);
        iv1.setFitWidth(150);
        iv1.setFitHeight(150);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setX(x);
        iv1.setY(y);
        if(rotate){
            Rotate rotate1 = new Rotate();
            rotate1.setAngle(90);
            rotate1.setPivotX(x+75);
            rotate1.setPivotY(y+75);
            iv1.getTransforms().add(rotate1);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.getChildren().add(iv1);
            }
        });
    }
}
