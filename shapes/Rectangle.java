package PaintBrush.paint_brush.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int x1, int y1, int x2, int y2,
            Color color, boolean isDotted, boolean isSolid) {
        super(x1, y1, x2, y2, color, isDotted, isSolid);
    }

    @Override
    public void draw(Graphics g) {

        width = getP2().getX() - getP1().getX();
        height = getP2().getY() - getP1().getY();

        int x1 = (width>=0)? getP1().getX(): getP2().getX();
        int y1 = (height >= 0)? getP1().getY(): getP2().getY();

        if (isSolid()) {
            g.setColor(getColor());
            g.fillRect(x1, y1,
                    Math.abs(width),
                    Math.abs(height));
        }

        if (isDotted()) {
            Graphics2D g2d = (Graphics2D) g;

            Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 5 },
                    0);
            g2d.setColor(getColor());
            g2d.setStroke(dashedStroke);
            g2d.drawRect(x1, y1,
                    Math.abs(width),
                    Math.abs(height));
            g2d.setStroke(new BasicStroke());
        } else {
            g.setColor(getColor());
            g.drawRect(x1, y1,
                    Math.abs(width),
                    Math.abs(height));
        }
    }

    public static void drawRect(Graphics g, boolean dotted, boolean fill,Color color,
    int x1, int y1, int x2, int y2) {
        

        int width = x2 - x1;
        int height = y2 - y1;

        x1 = (width>=0)? x1: x2;
        y1 = (height >= 0)? y1: y2;

        if (fill) {
            g.setColor(color);
            g.fillRect(x1, y1,
                    Math.abs(width),
                    Math.abs(height));
        }

        if (dotted) {
            Graphics2D g2d = (Graphics2D) g;

            Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 5 },
                    0);
            g2d.setColor(color);
            g2d.setStroke(dashedStroke);
            g2d.drawRect(x1, y1,
                    Math.abs(width),
                    Math.abs(height));
            g2d.setStroke(new BasicStroke());
        } else {
            g.setColor(color);
            g.drawRect(x1, y1,
                    Math.abs(width),
                    Math.abs(height));
        }
    }
}
