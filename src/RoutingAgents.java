import utils.GraphDrawable;

import javax.swing.*;

public class RoutingAgents {

    static final int NUM_OF_ITERATIONS = 100;
    static final int NUM_OF_AGENTS = 1000000;

    public static void main(String[] args) {
        GraphData GD = new GraphData();

        AgentModel agentModel = new AgentModel();
        agentModel.createAgentList(NUM_OF_AGENTS);

        JFrame frame = new JFrame("Calculating routes...");

        long min = Long.MAX_VALUE;
        for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
            frame.setTitle("Routes after iteration: " + i);
            GD.createRandomRoutes();
            long newTime = agentModel.calculateTravelTimes(GD);
            if (newTime < min) {
                min = newTime;
                System.out.println("Better route distribution - Iteration: " + i + " - Time: " + min);
                new GraphDrawable(GD.G, GD.coords, GD.routes).drawNewFrame(frame);
            }
        }
        System.out.println("After " + NUM_OF_ITERATIONS + " iterations, these are the best routes found.");
        frame.setTitle("Routes after iteration: " + NUM_OF_ITERATIONS + " - COMPLETED");
    }
}
