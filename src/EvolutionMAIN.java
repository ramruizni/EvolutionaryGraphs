import utils.GraphDrawable;

public class EvolutionMAIN {

    public static void main(String[] args) {
        GraphData GD = new GraphData();

        new GraphDrawable(GD.G, GD.coords, GD.routes).drawWithTitle("Routes Demo");

        AgentModel agentModel = new AgentModel();
        agentModel.createAgentList();
        System.out.println(agentModel.calculateTravelTimes(GD));
    }
}
