package Enemy;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class BaseEnemy extends ImageView {
    private Stage primaryStage;
    private int lives;
    private double height, width;

    public BaseEnemy(Stage primaryStage) {
        this.lives = 100;
        this.primaryStage = primaryStage;
        try {
            this.setImage(new Image(new FileInputStream("./src/img/Baloon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        show();

        move();
    }

    private void show() {
        this.setFitWidth(50);
        this.setFitHeight(71);

        this.setX(0);
        this.setY(200);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    private void move() {
        Thread t = new Thread(() -> {
            while(lives > 0 && this.getX() < primaryStage.getWidth()) {
                Platform.runLater(() -> this.setX(this.getX() + 1));
                Platform.runLater(() -> this.setY(this.getY()));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.setVisible(false);
        });

        t.setDaemon(true);
        t.start();
    }
}
