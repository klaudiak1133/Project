
package utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;


public class Util {
    
    public static Point centerWindow(int width, int height) {
        Toolkit toolkit= Toolkit.getDefaultToolkit();
        Dimension screenSize= toolkit.getScreenSize();
        int x= (int)(screenSize.getWidth()-width)/2;
        int y= (int)(screenSize.getHeight()-height)/2;
        return new Point(x,y);
    }
}
