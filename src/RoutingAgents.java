import utils.GraphDrawable;

import javax.swing.*;

public class RoutingAgents {
    static final int NUM_OF_ITERATIONS = 100;
    static final int NUM_OF_AGENTS = 1000000;

    static JFrame frame;
    static JFrame textFrame;
    static JTextArea textArea;
    static GraphData GD;
    static AgentModel agentModel;

    public static void main(String[] args) {
        GD = new GraphData();

        frame = new JFrame("Calculating routes...");
        textFrame = new JFrame("Routing Agents");

        textArea = new JTextArea();
        textFrame.add(textArea);
        textFrame.setSize(400, 600);
        textFrame.setVisible(true);

        initModel();
    }

    public static void initModel() {
        agentModel = new AgentModel();
        agentModel.createAgentList(NUM_OF_AGENTS);

        long min = Long.MAX_VALUE;
        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            frame.setTitle("Routes after iteration: " + i);
            GD.createRandomRoutes();
            long newTime = agentModel.calculateTravelTimes(GD);
            if (newTime < min) {
                min = newTime;
                System.out.println("Better route distribution - Iteration: " + i + " - Time: " + min);
                textArea.append("\nBetter route distribution - Iteration: " + i + " - Time: " + min);
                new GraphDrawable(GD.G, GD.coords, GD.routes).drawNewFrame(frame);
            }
        }
        textArea.append("\n\nAfter " + NUM_OF_ITERATIONS + " iterations, these are the best routes found.");
        System.out.println("After " + NUM_OF_ITERATIONS + " iterations, these are the best routes found.");
        frame.setTitle("Routes after iteration: " + NUM_OF_ITERATIONS + " - COMPLETED");
    }
}
