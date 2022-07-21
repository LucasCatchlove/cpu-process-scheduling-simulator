import java.util.Arrays;

public class Process {
    private String pid;
    private int arrivalTime;
    private int[] IORequests;
    private int currentIORequest = 0;

    public Process(String pid, int arrivalTime, int[] IORequests) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.IORequests = IORequests;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pid='" + pid + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", IORequests=" + Arrays.toString(IORequests) +
                ", currentIORequest=" + currentIORequest +
                "}\n";
    }

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
