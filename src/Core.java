
public class Core {

    private boolean isAssignedProcess = false;
    private Process currentProcess;
    private int startTime;
    private int id;

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
        System.out.println("-> core " + id + " assigned process [ " + currentProcess.getPid() + " | execution time: " + currentProcess.getExecTime()
                + " arrival time: " + currentProcess.getArrivalTime() + "]");

    }

    public void processIsComplete(int clock) {
        if(currentProcess != null && clock == startTime + currentProcess.getExecTime()+ currentProcess.getIORequests().size()*2) {
            System.out.println("-> Process " + currentProcess.getPid() + " on core " + id + " has completed");
            isAssignedProcess = false;
            currentProcess = null;
        }
    }

    public void processHasIO(int clock) {
        if(currentProcess != null && currentProcess.getIORequests().size() > 0 ) {
            if (clock == startTime + currentProcess.getIORequests().get(0)){
                System.out.println("-> Process " + currentProcess.getPid() + " on core " + id + " waiting for IO");
            } else if (clock == startTime + currentProcess.getIORequests().get(0) + 2) {
                System.out.println("-> IO on Process " + currentProcess.getPid() + " on core " + id + " has completed");
                currentProcess.getIORequests().remove(0);
            }
        }
    }


    public void ioIsScheduled(int clock) {




    }

    public int getId() {
        return id;
    }
    
}
