package PaintBrush.paint_brush;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Stack;

import PaintBrush.paint_brush.shapes.Eraser;
import PaintBrush.paint_brush.shapes.Line;
import PaintBrush.paint_brush.shapes.Oval;
import PaintBrush.paint_brush.shapes.Pencel;
import PaintBrush.paint_brush.shapes.Rectangle;
import PaintBrush.paint_brush.shapes.Shape;

public class PaintBrush extends Applet {

    private static final int LINE = 0;
    private static final int RECT = 1;
    private static final int OVAL = 2;
    private static final int PENCEL = 4;
    private static final int ERASER = 5;

    private int initial_x;
    private int initial_y;
    private int current_x;
    private int current_y;
    private boolean isDraged;

    private Stack<Shape> shapes;
    private Label appLabel;
    private Button lineBtn;
    private Button rectBtn;
    private Button ovalBtn;
    private Button pencelBtn;
    private Button eraseBtn;
    private Checkbox fillBtn;
    private Checkbox dottedBtn;
    private Button blackBtn;
    private Button redBtn;
    private Button greenBtn;
    private Button blueBtn;
    private Button clearBtn;
    private Button undoBtn;
    private Label selections;

    private int selectedShape;
    private Color selectedColor;

    @Override
    public void init() {

        shapes = new Stack<Shape>();

        appLabel = new Label("Paint Brush");
        appLabel.setAlignment(Label.CENTER);
        appLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        appLabel.setSize(getWidth(), getHeight());
        lineBtn = new Button("Line");
        rectBtn = new Button("Rectangle");
        ovalBtn = new Button("Oval");
        pencelBtn = new Button("Pencil");
        eraseBtn = new Button("Erase");
        fillBtn = new Checkbox("Fill");
        dottedBtn = new Checkbox("Doted Strouck");

        blackBtn = new Button("Black");
        blackBtn.setBackground(Color.BLACK);
        blackBtn.setForeground(Color.BLACK);

        redBtn = new Button("Red");
        redBtn.setBackground(Color.RED);
        redBtn.setForeground(Color.RED);

        greenBtn = new Button("Green");
        greenBtn.setBackground(Color.GREEN);
        greenBtn.setForeground(Color.GREEN);

        blueBtn = new Button("Blue");
        blueBtn.setBackground(Color.BLUE);
        blueBtn.setForeground(Color.BLUE);

        selections = new Label("Black, Line");

        clearBtn = new Button("Clear");
        undoBtn = new Button("Undo");

        add(appLabel);
        add(lineBtn);
        add(rectBtn);
        add(ovalBtn);
        add(pencelBtn);
        add(eraseBtn);
        add(fillBtn);
        add(dottedBtn);
        add(blackBtn);
        add(redBtn);
        add(greenBtn);
        add(blueBtn);
        add(clearBtn);
        add(undoBtn);
        add(selections);

        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = LINE;
                updateSelections();
            }
        });

        rectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = RECT;
                updateSelections();
            }
        });

        ovalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = OVAL;
                updateSelections();
            }
        });

        pencelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = PENCEL;
                updateSelections();
            }
        });

        eraseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = ERASER;
                updateSelections();
            }
        });

        fillBtn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateSelections();
            }
        });

        dottedBtn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateSelections();
            }
        });

        blackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = Color.BLACK;
                updateSelections();
            }
        });

        redBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = Color.RED;
                updateSelections();
            }
        });

        greenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = Color.GREEN;
                updateSelections();
            }
        });

        blueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = Color.BLUE;
                updateSelections();
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                repaint();
            }
        });

        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!shapes.isEmpty()) {
                    shapes.pop();
                    repaint();
                }
            }
        });

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (selectedShape) {
                    case ERASER:
                        shapes.push(new Eraser(e.getX(), e.getY(), Eraser.eraserDimention));

                        repaint(e.getX(), e.getY(),
                                Eraser.eraserDimention,
                                Eraser.eraserDimention);
                        break;
                    case PENCEL:
                        shapes.push(new Pencel(e.getX(), e.getY(), Pencel.pencelDimention, selectedColor));
                        repaint(e.getX(), e.getY(),
                                Pencel.pencelDimention,
                                Pencel.pencelDimention);
                        break;
                    case LINE:
                    case RECT:
                    case OVAL:
                        initial_x = e.getX();
                        initial_y = e.getY();
                        current_x = e.getX();
                        current_y = e.getY();

                        break;
                }

                isDraged = false;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                isDraged = true;
                switch (selectedShape) {
                    case ERASER:
                        int eraserDimention = 15;
                        shapes.push(new Eraser(e.getX(), e.getY(), eraserDimention));

                        repaint(e.getX(), e.getY(),
                                Eraser.eraserDimention,
                                Eraser.eraserDimention);
                        break;
                    case PENCEL:
                        int pencelDimention = 5;
                        shapes.push(new Pencel(e.getX(), e.getY(), pencelDimention, selectedColor));
                        repaint(e.getX(), e.getY(),
                                Pencel.pencelDimention,
                                Pencel.pencelDimention);
                        break;

                    case LINE:
                    case RECT:
                    case OVAL:
                        current_x = e.getX();
                        current_y = e.getY();
                        repaint();
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDraged) {
                    isDraged = false;
                    switch (selectedShape) {
                        case LINE:
                            shapes.push(new Line(initial_x, initial_y, current_x, current_y,
                                    selectedColor, dottedBtn.getState()));
                            repaint();
                            break;
                        case RECT:
                            shapes.push(new Rectangle(initial_x, initial_y, current_x, current_y,
                                    selectedColor, dottedBtn.getState(), fillBtn.getState()));
                            repaint();
                            break;
                        case OVAL:
                            shapes.push(new Oval(initial_x, initial_y, current_x, current_y,
                                    selectedColor, dottedBtn.getState(), fillBtn.getState()));
                            repaint();
                            break;
                        case ERASER:
                            shapes.push(new Eraser(e.getX(), e.getY(), Eraser.eraserDimention));

                            repaint(e.getX(), e.getY(),
                                    Pencel.pencelDimention,
                                    Pencel.pencelDimention);
                            break;
                        case PENCEL:
                            shapes.push(new Pencel(e.getX(), e.getY(), Pencel.pencelDimention, selectedColor));

                            repaint(e.getX(), e.getY(),
                                    Pencel.pencelDimention,
                                    Pencel.pencelDimention);
                            break;
                    }
                }
            }

        };

        addMouseListener(adapter);
        addMouseMotionListener(adapter);

        selectedShape = LINE;
        selectedColor = Color.BLACK;
        updateSelections();
    }

    @Override
    public void paint(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
        if (isDraged)
            drawCurrenShape(g);
    }

    private void drawCurrenShape(Graphics g) {
        switch (selectedShape) {
            case LINE:
                Line.drawLine(g, dottedBtn.getState(), selectedColor,
                        initial_x, initial_y, current_x, current_y);
                break;
            case RECT:
                Rectangle.drawRect(g, dottedBtn.getState(),
                        fillBtn.getState(), selectedColor,
                        initial_x, initial_y, current_x, current_y);
                break;
            case OVAL:
                Oval.drawOval(g, dottedBtn.getState(),
                        fillBtn.getState(), selectedColor,
                        initial_x, initial_y, current_x, current_y);
                break;
        }
    }

    private void updateSelections() {
        String color = "";
        String shape = "";
        String displayText;

        if (selectedColor.equals(Color.black)) {
            color = "Black";
        } else if (selectedColor.equals(Color.RED)) {
            color = "Red";
        } else if (selectedColor.equals(Color.GREEN)) {
            color = "Greem";
        } else if (selectedColor.equals(Color.BLUE)) {
            color = "Blue";
        }

        switch (selectedShape) {
            case LINE:
                shape = "Line";
                break;
            case RECT:
                shape = "Rectangle";
                break;
            case OVAL:
                shape = "Oval";
                break;
            case PENCEL:
                shape = "Pencel";
                break;
            case ERASER:
                shape = "Ereaser";
                break;

            default:
                break;
        }

        displayText = color + ", " + shape;

        if (fillBtn.getState()) {
            displayText += ", " + "F";
        }

        if (dottedBtn.getState()) {
            displayText += ", " + "D";
        }

        selections.setForeground(selectedColor);
        selections.setText(displayText);
    }
}
