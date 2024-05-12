package animation;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimationNode {
        private ImageView imageView;
        private AnimationNode next;

        public AnimationNode(String url,double width, double height,double layoutX, double layoutY) {
            ImageView imageView = new ImageView(new Image(url));
            imageView.setFitHeight(height);
            imageView.setFitWidth(width);
            imageView.setLayoutX(layoutX);
            imageView.setLayoutY(layoutY);
            this.setImageView(imageView);
            this.next = null;
        }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public AnimationNode getNext() {
            return next;
        }

        public void setNext(AnimationNode next) {
            this.next = next;
        }
}
