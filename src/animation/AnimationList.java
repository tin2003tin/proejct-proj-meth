package animation;

public class AnimationList {
    private AnimationNode head;
    private AnimationNode tail;
    private int duration;
    private AnimationNode current;

    public AnimationList() {
        this.head = null;
        this.tail = null;
        this.current = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addAFrame(String url,double width, double height,double layoutX, double layoutY) {
        AnimationNode newNode = new AnimationNode(url,width,height,layoutX,layoutY);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            tail.setNext(head);
            current = head;
        } else {
            tail.setNext(newNode);
        }
        newNode.setNext(head);
        tail = newNode;
    }

    public void addAnimation(String[] urls ,double width, double height,double layoutX, double layoutY) {
        for (String url: urls) {
            addAFrame(url,width,height,layoutX,layoutY);
        }
    }

    public void ResetNode() {
        if (isEmpty()) {
            return;
        } else {
            current = head;
        }
    }

    public AnimationNode getNextAnimation() {
        if (isEmpty()) {
            return null;
        } else {
            current = current.getNext();
            return current;
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public AnimationNode getCurrent() {
        return current;
    }
}