package snake;

abstract class Square {
    protected int x;
    protected int y;
    protected int size;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean hasCollisionPoint(Square other) {
        return other.x + other.size > x && x + size > other.x
                && other.y + other.size > y && y + size > other.y;
    }

}
