package snake;

import java.util.*;

public class Food extends Square{

    private final int maxWidth;
    private final int maxHeight;
    private Random random;

    public Food(int size, int maxWidth, int maxHeight) {
        this.size=size;
        this.maxWidth = maxWidth-size;
        this.maxHeight = maxHeight-size;
        random= new Random();
        changePosition();
    }
        public final void shouldChangePosition (boolean change) {
            if(change){
                changePosition();
            }
        }

    private void changePosition() {
        this.x=random.nextInt(maxWidth);
        this.y= random.nextInt(maxHeight);
    }

}
