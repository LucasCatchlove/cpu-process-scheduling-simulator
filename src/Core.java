import java.util.Queue;

public class Core {

    private boolean isAssignedProcess = false;
    private Process currentProcess;
    private int startTime;
    private int id;
    private Queue<IOREquest>

    public Core(int id) {
        this.id = id;
    }


    public boolean isAssignedProcess() {
        return isAssignedProcess;
    }

    public void addProcess(Process p, int clock) {
        currentProcess = p;
        startTime = clock;
        isAssignedProcess = true;
        System.out.println("-> core " + id +
                " assigned process [ " + currentProcess.getPid() +
                " | execution time: " + currentProcess.getExecTime() +
                " | arrival time: " + currentProcess.getArrivalTime() +
                " | I/O: " + currentProcess.getIORequests() + " ]");

    }


    public void processIsComplete(int clock) {
        if ((currentProcess != null && clock >= startTime + currentProcess.getExecTime() /*+ currentProcess.getNumRequests() * 2) && !currentProcess.isWaitingForIO()*/)) {
            System.out.println("-> Process " + currentProcess.getPid() +
                    " on core " + id + " has completed");
            isAssignedProcess = false;
            currentProcess = null;
        }
    }

    public void processIsComplete(int clock, int timeQ) {

    }

    public void processHasIO(int clock) {
        if (currentProcess != null) {

        }
    }


    public int getId() {
        return id;
    }




}
