package PaintBrush.paint_brush.shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
    private Point p1;
    private Point p2;
    private Color color;
    private boolean isDotted;
    private boolean isSolid;

    public Shape(int x1, int y1,
            int x2, int y2,
            Color color,
            boolean isDotted, boolean isSolid) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
        this.color = color;
        this.isDotted = isDotted;
        this.isSolid = isSolid;
    }

    public Point getP1() {
        return p1;
    }

    public void setp1(int x, int y) {
        this.p1.setX(x);
        this.p1.setY(y);
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(int x, int y) {
        this.p2.setX(x);
        this.p2.setY(y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDotted() {
        return isDotted;
    }

    public void setDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    public abstract void draw(Graphics g);
}
