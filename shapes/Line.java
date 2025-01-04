package PaintBrush.paint_brush.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Line extends Shape {
    public Line(int x1, int y1,
            int x2, int y2,
            Color color, boolean isDotted) {
        super(x1, y1, x2, y2, color, isDotted, true);
    }

    @Override
    public void draw(Graphics g) {
        if (isDotted()) {
            Graphics2D g2d = (Graphics2D) g;
            
            Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 5 },
                    0);

            g2d.setColor(getColor());
            g2d.setStroke(dashedStroke);
            g2d.drawLine(getP1().getX(), getP1().getY(),
                    getP2().getX(), getP2().getY());
            g2d.setStroke(new BasicStroke());
        } else {
            g.setColor(getColor());
            g.drawLine(getP1().getX(), getP1().getY(),
                    getP2().getX(), getP2().getY());
        }
    }

    public static void drawLine(Graphics g, boolean dotted, Color color, int x1, int y1, int x2, int y2) {
        if (dotted) {
            Graphics2D g2d = (Graphics2D) g;
            
            Stroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 5 },
                    0);

            g2d.setColor(color);
            g2d.setStroke(dashedStroke);
            g2d.drawLine(x1, y1, x2, y2);
            g2d.setStroke(new BasicStroke());
        } else {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
