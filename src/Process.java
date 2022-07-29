import java.util.Arrays;
import java.util.List;

public class Process {
    private final String pid;
    private final int arrivalTime;
    private final int execTime;
    private final List<Integer> IORequests;
    private final int numRequests;

    private int currentIORequest = 0;
    private int delay = 0;

    private boolean waitingForIO = false;
    private boolean hasWaitingRequest = false;
    private int waitingIORequest = 0;

    private int currentIORequestStartTime = 0;

    public Process(String pid, int arrivalTime, int execTime, List<Integer> IORequests) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.IORequests = IORequests;
        this.execTime = execTime;
        this.numRequests = IORequests.size();
    }

    public int getExecTime() {
        return execTime;
    }

    public String getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public List<Integer> getIORequests() {
        return IORequests;
    }

    public int getCurrentIORequest() {
        return currentIORequest;
    }

    public void setNextIORequest() {
        currentIORequest++;
    }

    public void setDelay(int d) {
        delay = d;
    }

    public int getDelay() {
        return delay;
    }

    public int getNumRequests() {
        return numRequests;
    }

    public boolean isWaitingForIO() {
        return waitingForIO;
    }

    public void setWaitingForIO(boolean r) {
        waitingForIO = r;
    }

    public void setWaitingIORequest(int r) {
        waitingIORequest = r;
    }

    public int getWaitingIORequest() {
        return waitingIORequest;
    }

    public int getCurrentIORequestStartTime() {
        return currentIORequestStartTime;
    }

    public void setCurrentIORequestStartTime(int currentIORequestStartTime) {
        this.currentIORequestStartTime = currentIORequestStartTime;
    }

    public boolean hasWaitingRequest() {
        return hasWaitingRequest;
    }

    public void setHasWaitingRequest(boolean hasWaitingRequest) {
        this.hasWaitingRequest = hasWaitingRequest;
    }

    @Override
    public String toString() {
        return "Process{" +
                "arrivalTime=" + arrivalTime +
                ", execTime=" + execTime +
                '}';
    }
}
