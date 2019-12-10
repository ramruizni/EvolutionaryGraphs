import data.structures.DirectedEdge;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgentModel {
    List<Agent> agentList = new ArrayList<>();
    Random random = new Random();
    int numberOfAgents;
    int waitingTimeAvg = 0;
    int minutesWaitingNewBus;
    int minutesInRouteWithoutStop;

    AgentModel(int minutesWaitingNewBus, int minutesInRouteWithoutStop) {
        if (minutesInRouteWithoutStop < 1 || minutesInRouteWithoutStop > 8) {
            throw new InvalidParameterException("Minutes in Route must be between 1 and 8\nfor the model to work");
        }
        this.minutesWaitingNewBus = minutesWaitingNewBus;
        this.minutesInRouteWithoutStop = minutesInRouteWithoutStop;
    }

    public void createAgentList(int numberOfAgents) {
        this.numberOfAgents = numberOfAgents;
        for (int i = 0; i < numberOfAgents; i++) {
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
            boolean inRoute = false;
            for (DirectedEdge edge : path) {
                if (GD.edgeHasRoute.get(edge.getStart() + " " + edge.getEnd()) != -1) {
                    travelDuration += minutesInRouteWithoutStop; // journey is faster if the user is in a route
                    if (!inRoute) waitingTimeAvg += minutesWaitingNewBus / numberOfAgents; // if it had to wait, we increase the sum
                    inRoute = true;
                } else {
                    if (inRoute) waitingTimeAvg += minutesWaitingNewBus / numberOfAgents;
                    travelDuration += edge.weight();
                    inRoute = false;
                }
            }
            duration += travelDuration;
        }
        return duration;
    }
}
