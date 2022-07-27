
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
        System.out.println("-> core " + id +
                " assigned process [ " + currentProcess.getPid() +
                " | execution time: " + currentProcess.getExecTime() +
                " | arrival time: " + currentProcess.getArrivalTime() +
                " | I/O: " + currentProcess.getIORequests() + " ]");

    }



    public void processIsComplete(int clock) {
//        for(int requestStartTime : currentProcess.getIORequests()) {
//            if(startTime + currentProcess.getExecTime() < startTime + requestStartTime + 2) {
//                System.out.println("-> IO started at time " + requestStartTime +
//                        " on Process " + currentProcess.getPid() +
//                        " on core " + id + " has completed");
//                System.out.println("-> Process " + currentProcess.getPid() +
//                        " on core " + id + " has completed");
//            }
//
//        }
        if(currentProcess != null && clock == startTime + currentProcess.getExecTime() /*+ currentProcess.getNumRequests()*2*/) {
            System.out.println("-> Process " + currentProcess.getPid() +
                               " on core " + id + " has completed");
            isAssignedProcess = false;
            currentProcess = null;
        }
    }

//    public void processHasIO(int clock) {
//        if(currentProcess != null && currentProcess.getNumRequests() > 0 && currentProcess.getCurrentIORequest() < (currentProcess.getNumRequests())) {
//
//            while(clock == startTime + currentProcess.getIORequests().get(currentProcess.getCurrentIORequest())) {
//                System.out.println("-> Process " + currentProcess.getPid() + " on core " + id + " waiting for IO");
//                if(currentProcess.getCurrentIORequest() == currentProcess.getNumRequests() - 1) break;
//                currentProcess.setNextIORequest();
//            }
//            for(int reqTime : currentProcess.getIORequests()) {
//                if(clock == startTime + reqTime + 2)
//                System.out.println("-> IO started at time " + reqTime +
//                " on Process " + currentProcess.getPid() +
//                " on core " + id + " has completed");
//            }
//
//
//        }
//    }



    public int getId() {
        return id;
    }

}
