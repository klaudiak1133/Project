package snake;

import utils.*;

public class SnakePart extends Square {

    SnakePart nextPart;
    Direction direction;

    public SnakePart(int x, int y, int size, Direction direction) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.direction = direction;
        calculateCoordinateByDirection(-direction.xMove, -direction.yMove);
    }

    SnakePart(SnakePart current) {
        setNewState(current);
    }

    boolean hasNextPart() {
        return nextPart != null;
    }

    private void calculateCoordinateByDirection(int xMove, int yMove) {
        this.x +=(size * xMove);
        this.y += (size * yMove);
    }


    void move(Direction direction) {
        SnakePart previousState = new SnakePart(this);
        calculateCoordinateByDirection(direction.xMove, direction.yMove);
        this.direction = direction;
        pullNextPart(previousState);
    }

    private void pullNextPart(SnakePart newState) {
        if (hasNextPart()) {
            SnakePart previousState = new SnakePart(nextPart);
            nextPart.setNewState(newState);
            nextPart.pullNextPart(previousState);

        }
    }

    private void setNewState(SnakePart newState) {
        this.x = newState.x;
        this.y = newState.y;
        this.direction = newState.direction;
        this.size = newState.size;
    }

    void addTail() {
        if (hasNextPart()) {
            nextPart.addTail();
            
        } else {
            nextPart = new SnakePart(x, y, size, direction);
        }
    }
}
