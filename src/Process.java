import java.util.Arrays;

public class Process {
    private final String pid;
    private final int arrivalTime;
    private final int execTime;
    private final int[] IORequests;
    private int currentIORequest = 0;

    public Process(String pid, int arrivalTime, int execTime, int[] IORequests) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.IORequests = IORequests;
        this.execTime = execTime;
    }

    public int getExecTime() { return execTime; }

    public String getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int[] getIORequests() {
        return IORequests;
    }

    public int getCurrentIORequest() {
        return currentIORequest;
    }

    public void setCurrentIORequest(int currentIORequest) {
        this.currentIORequest = currentIORequest;
    }

}
