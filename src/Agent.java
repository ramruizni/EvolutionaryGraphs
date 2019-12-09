public class Agent {
    int startNode;
    int endNode;
    long startTime;
    long endTime;

    public Agent(int startNode, int endNode, long startTime) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
