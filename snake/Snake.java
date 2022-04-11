package snake;

import utils.*;

import java.util.*;

public class Snake implements Iterable<SnakePart> {

    private final int AREA_WIDTH;
    private final int AREA_HEIGHT;

    private SnakePart head;
    private int length;

    public Snake(int startX, int startY, int startLength, int size, Direction direction, int areaWidth, int areaHeight) {
        head = new SnakePart(startX, startY, size, direction);
        AREA_WIDTH = areaWidth - size;
        AREA_HEIGHT = areaHeight - size;
        length = 1;
        createSnakeBody(length, startLength);
        
     
    }

    public int getLength() {
        return length;
    }

    private void createSnakeBody(int length, int startLength) {
        for (int i = length; i < startLength; i++) {
            grow();
        }
    }

    private void grow() {
        head.addTail();
        length++;
    }

    public boolean eat(Food food) {
        boolean ate = head.hasCollisionPoint(food);
        if (ate) {
            grow();
        }
        return ate;
    }

    public boolean move(Direction direction) {
        if (goodDirection(direction)) {
            head.move(direction);
        }
        return canMove();
    }

    private boolean goodDirection(Direction direction) {
        return !head.direction.isOppositeDirection(direction);
    }

    private boolean canMove() {
        return withinTheArea() && withoutSelfCollision();
    }

    private boolean withinTheArea() {
        return head.x > 0 && head.x < AREA_WIDTH &&
                head.y > 0 && head.y < AREA_HEIGHT;
    }

    private boolean withoutSelfCollision() {
        SnakePart part = head.nextPart;
        boolean result = true;
        while (part != null && result) {
            result = !head.hasCollisionPoint(part);
            part = part.nextPart;
        }
        return result;
    }




    @Override
    public Iterator<SnakePart> iterator() {
        return new SnakeIterator();
    }

    private class SnakeIterator implements Iterator<SnakePart> {

        private SnakePart currentPart;

        public SnakeIterator() {
            currentPart = Snake.this.head;
        }

        @Override
        public boolean hasNext() {
            return currentPart !=null;
        }

        @Override
        public SnakePart next() {
            SnakePart result= new SnakePart(currentPart);
            currentPart = currentPart.nextPart;
            return result;
        }
    }
}
