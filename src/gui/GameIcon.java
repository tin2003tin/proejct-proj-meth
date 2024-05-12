package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameIcon {
    public static ImageView startButton(double scale) {
        Image image = new Image("icon/startButton.png");

        ImageView imageView = new ImageView(image);
        double real_sale = (double) scale /5;
        imageView.setScaleX(real_sale);
        imageView.setScaleY(real_sale);

        return imageView;
    }
    public static ImageView parseButton(double scale) {
        Image image = new Image("icon/parseButton.png");

        ImageView imageView = new ImageView(image);
        double real_sale = (double) scale /5;
        imageView.setScaleX(real_sale);
        imageView.setScaleY(real_sale);

        return imageView;
    }

    public static ImageView stopButton(double scale) {
        Image image = new Image("icon/stopButton.png");

        ImageView imageView = new ImageView(image);
        double real_sale = (double) scale /5;
        imageView.setScaleX(real_sale);
        imageView.setScaleY(real_sale);

        return imageView;
    }
    public static ImageView gameoverIcon(double width,double height) {
        Image image = new Image("icon/gameoverIcon.png");

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        return imageView;
    }
    public static ImageView newgameIcon(double scale) {
        Image image = new Image("icon/newgameIcon.png");

        ImageView imageView = new ImageView(image);
        double real_sale = (double) scale /5;
        imageView.setScaleX(real_sale);
        imageView.setScaleY(real_sale);

        return imageView;
    }
    public static ImageView fullHeart(int size) {
        Image image = new Image("icon/fullHeart.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);

        return imageView;
    }
    public static ImageView halfHeart(int size) {
        Image image = new Image("icon/halfHeart.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);

        return imageView;
    }
    public static ImageView emptyHeart(int size) {
        Image image = new Image("icon/emptyHeart.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);

        return imageView;
    }
    public static ImageView redPotion(double scale) {
        Image image = new Image("icon/redPotion.png");

        ImageView imageView = new ImageView(image);
        double real_sale = (double) scale /5;
        imageView.setScaleX(real_sale);
        imageView.setScaleY(real_sale);

        return imageView;
    }
    public static ImageView bluePotion(double scale) {
        Image image = new Image("icon/bluePotion.png");

        ImageView imageView = new ImageView(image);
        double real_sale = (double) scale /5;
        imageView.setScaleX(real_sale);
        imageView.setScaleY(real_sale);

        return imageView;
    }
    public static ImageView yellowPotion(double scale) {
        Image image = new Image("icon/yellowPotion.png");

        ImageView imageView = new ImageView(image);
        double real_sale = (double) scale /5;
        imageView.setScaleX(real_sale);
        imageView.setScaleY(real_sale);

        return imageView;
    }
}