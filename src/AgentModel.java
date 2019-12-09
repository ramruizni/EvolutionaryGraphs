import data.structures.DirectedEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgentModel {
    List<Agent> agentList = new ArrayList<>();
    Random random = new Random();

    public void createAgentList(int amount) {
        for (int i = 0; i < amount; i++) {
            int startNode = random.nextInt(53) + 1;
            int endNode = startNode;
            while (endNode == startNode) {
                endNode = random.nextInt(53) + 1;
            }
            agentList.add(new Agent(startNode, endNode, 0));
        }
    }

    public long calculateTravelTimes(GraphData GD) {
        long duration = 0;
        for (Agent agent : agentList) {
            //System.out.println("Start: " + agent.startNode + ", End: " + agent.endNode);
            Iterable<DirectedEdge> path = GD.FW.path(agent.startNode, agent.endNode);
            long travelDuration = 0;
            for (DirectedEdge edge : path) {
                boolean [] routes = GD.edgeRoutes.get(edge.getStart() + " " + edge.getEnd());
                if (GD.edgeHasRoute.get(edge.getStart() + " " + edge.getEnd())) {
                    travelDuration += 5;
                    //System.out.println("adding " + 5 + " for edge: " + edge);
                } else {
                    travelDuration += edge.weight();
                    //System.out.println("adding " + edge.weight() + " for edge: " + edge);
                }
            }
            duration += travelDuration;
        }
        return duration;
    }
}
