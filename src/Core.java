
public class Core {

    private boolean isAssignedProcess = false;
    private Process currentProcess;
    private int startTime;
    private int id;

    public Core(int id) {
        this.id = id;
    }

    public void addProcess(Process p, int clock) {
        currentProcess = p;
        startTime = clock;
        isAssignedProcess = true;
        System.out.println("core " + id + " assigned process [" + currentProcess.getPid() + " | exec: " + currentProcess.getExecTime()
                + " at: " + currentProcess.getArrivalTime() + "] at time " + clock);

    }

    public boolean isAssignedProcess() {
        return isAssignedProcess;
    }

    public void processIsComplete(int clock) {
        if(currentProcess != null && clock == startTime + currentProcess.getExecTime()) {
            System.out.println("Process " + currentProcess.getPid() + " on core " + id + " has completed");
            isAssignedProcess = false;
            currentProcess = null;
        }
    }

    public void ioIsScheduled(int clock) {




    }

    public int getId() {
        return id;
    }
    
}
