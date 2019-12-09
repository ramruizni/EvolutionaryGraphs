package utils;

import data.structures.AdjMatrixEdgeWeightedDigraph;
import data.structures.DirectedEdge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphDrawable extends JPanel {

    private AdjMatrixEdgeWeightedDigraph G;
    private int[][] coords;
    private BufferedImage image;
    private ArrayList<ArrayList<Integer>> routes;

    public GraphDrawable(AdjMatrixEdgeWeightedDigraph G, int[][] coords, ArrayList<ArrayList<Integer>> routes) {
        this.G = G;
        this.coords = coords;
        this.routes = routes;
        try {
            image = ImageIO.read(new File("red_map.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.drawImage(image, 0, 0, this);

        drawEdges(g, Color.BLACK);
        drawAllRoutes(g);
        drawNodes(g, Color.BLUE);
    }

    private void drawNodes(Graphics g, Color color) {
        g.setColor(color);
        for (int v = 0; v < coords.length; v++) {
            g.fillOval(coords[v][0] - 5, coords[v][1] - 5, 10, 10);
            g.drawString(Integer.toString(v), coords[v][0] - 5, coords[v][1] - 5);
        }
    }

    private void drawEdges(Graphics g, Color color) {
        g.setColor(color);
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.doAdj(v)) {
                g.drawLine(coords[v][0], coords[v][1], coords[e.to()][0], coords[e.to()][1]);
                int edgeX = (coords[v][0] + coords[e.to()][0]) / 2;
                int edgeY = (coords[v][1] + coords[e.to()][1]) / 2;
                g.drawString(Integer.toString((int) e.weight()), edgeX, edgeY);

            }
        }
    }

    private void drawAllRoutes(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(4));
        for (int i = 0; i < routes.size(); i++) {
            drawRoute(g, i, routes);
        }
    }

    private void drawRoute(Graphics g, int i, ArrayList<ArrayList<Integer>> routes) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(4));
        g2.setColor(chooseColor(i));

        ArrayList<Integer> r = routes.get(i);
        int p = 0;
        for (int n = 1; n < r.size(); n++) {
            g2.draw(new Line2D.Float(coords[r.get(p)][0], coords[r.get(p)][1], coords[r.get(n)][0], coords[r.get(n)][1]));
            p++;
        }
    }

    private Color chooseColor(int i) {
        switch (i) {
            case 0:
                return Color.MAGENTA;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.CYAN;
            case 3:
                return Color.RED;
            case 4:
                return Color.PINK;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.BLACK;
            case 7:
                return Color.YELLOW;
        }
        return null;
    }

    public void drawNewFrame(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(800, 600);
        frame.setLocation(400, 0);
        frame.setVisible(true);
    }
}
