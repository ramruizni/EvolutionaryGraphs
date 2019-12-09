import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgentModel {
    List<Agent> agentList = new ArrayList<>();
    Random random = new Random();

    public void createAgentList() {
        for (int i = 0; i < 1000; i++) {
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
            duration += GD.FW.dist(agent.startNode, agent.endNode);
        }
        return duration;
    }
}
