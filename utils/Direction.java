package utils;

public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    public final int xMove;
    public final int yMove;

    private Direction(int xMove, int yMove) {
        this.xMove = xMove;
        this.yMove = yMove;
    }

    public boolean isOppositeDirection(Direction other) {
        boolean notSame= this!=other;
        boolean direction= isDirectionOkay(other);
        return notSame && direction;
    }

    private boolean isDirectionOkay(Direction other) {
        boolean xEquals=xMove - other.xMove == 0 &&
                yMove - other.yMove != 0;
        boolean yEquals=xMove - other.xMove != 0 &&
                yMove - other.yMove == 0;
       
        return xEquals || yEquals;
    }

}
