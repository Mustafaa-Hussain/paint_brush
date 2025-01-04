package PaintBrush.paint_brush.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Eraser extends Shape {
    public static int eraserDimention;

    public Eraser(int x, int y, int eraserDimention) {
        super(x, y, x + eraserDimention, y + eraserDimention, Color.white, false, true);

        Eraser.eraserDimention = eraserDimention;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getP1().getX(),
                getP1().getY(),
                eraserDimention,
                eraserDimention);
    }
}
