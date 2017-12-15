import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawGraph extends JPanel {

    private AdjMatrixEdgeWeightedDigraph G;
    private int[][] coord;
    private BufferedImage image;
    private ArrayList<ArrayList<Integer>> routes, newRoutes;

    DrawGraph(AdjMatrixEdgeWeightedDigraph G, int[][] coord, ArrayList<ArrayList<Integer>> routes,
              ArrayList<ArrayList<Integer>> newRoutes) {
        this.G = G;
        this.coord = coord;
        this.routes = routes;
        this.newRoutes = newRoutes;
        try {
            image = ImageIO.read(new File("redMap.JPG"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.drawImage(image, 0, 0, this);

        drawEdges(g, Color.BLACK);
        drawAllRoutes(g);
        drawNodes(g, Color.BLUE);
    }

    private void drawNodes(Graphics g, Color color){
        g.setColor(color);
        for (int v=0; v<coord.length; v++) {
            g.fillOval(coord[v][0]-5, coord[v][1]-5, 10, 10);
            g.drawString(Integer.toString(v), coord[v][0]-5, coord[v][1]-5);
        }
    }

    private void drawEdges(Graphics g, Color color) {
        g.setColor(color);
        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.doAdj(v)){
                g.drawLine(coord[v][0], coord[v][1], coord[e.to()][0], coord[e.to()][1]);
                int edgeX = (coord[v][0]+coord[e.to()][0])/2;
                int edgeY = (coord[v][1]+coord[e.to()][1])/2;
                g.drawString(Integer.toString((int)e.weight()), edgeX, edgeY);

            }
        }
    }

    private void drawAllRoutes(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(4));
        for(int i=0; i<routes.size(); i++) {
            if(routes == newRoutes)
                drawRoute(g, i, routes);
            else if(!(routes.get(i)).equals(newRoutes.get(i)))
                drawRoute(g, i, newRoutes);
        }
    }

    private void drawRoute(Graphics g, int i, ArrayList<ArrayList<Integer>> rouuutes){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(4));
        g2.setColor(chooseColor(i));

        ArrayList<Integer> r = rouuutes.get(i);
        int p = 0;
        for (int n = 1; n < r.size(); n++) {
            g2.draw(new Line2D.Float(coord[r.get(p)][0], coord[r.get(p)][1], coord[r.get(n)][0], coord[r.get(n)][1]));
            p++;
        }
    }

    private Color chooseColor(int i) {
        switch (i) {
            case 0: return Color.MAGENTA;
            case 1: return Color.GREEN;
            case 2: return Color.CYAN;
            case 3: return Color.RED;
            case 4: return Color.PINK;
            case 5: return Color.ORANGE;
            case 6: return Color.BLACK;
            case 7: return Color.YELLOW;
        }return null;
    }

}
