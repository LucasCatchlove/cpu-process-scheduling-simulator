
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
        if ((currentProcess != null && clock >= startTime + currentProcess.getExecTime() + currentProcess.getNumRequests() * 2) && !currentProcess.isWaitingForIO()) {
            System.out.println("-> Process " + currentProcess.getPid() +
                    " on core " + id + " has completed");
            isAssignedProcess = false;
            currentProcess = null;
        }
    }

    public void processIsComplete(int clock, int timeQ) {
        if ((currentProcess != null && clock >= startTime + currentProcess.getExecTime() + currentProcess.getNumRequests() * 2) && !currentProcess.isWaitingForIO()) {
            System.out.println("-> Process " + currentProcess.getPid() +
                    " on core " + id + " has completed");
            isAssignedProcess = false;
            currentProcess = null;
        }
    }

    public void IOScheduler(int clock) {
        if (currentProcess != null) {
            //terminate IO process and then begin the next if there was one waiting
            if (currentProcess.getCurrentIORequestStartTime() != -1 && clock == currentProcess.getCurrentIORequestStartTime() + 2) {

                currentProcess.setWaitingForIO(false);
                System.out.println("-> IO #" + (currentProcess.getCurrentIORequest()) +
                        " started at time " + currentProcess.getCurrentIORequestStartTime() +
                        " on Process " + currentProcess.getPid() +
                        " on core " + id + " has completed");

                if(currentProcess.hasWaitingRequest()) {
                    System.out.println("-> Process " + currentProcess.getPid() +
                            " on core " + id +
                            " waiting for IO #" + (currentProcess.getCurrentIORequest()+1) +
                            " (request was delayed)");
                    currentProcess.setCurrentIORequestStartTime(clock);
                    currentProcess.setWaitingForIO(true);
                    currentProcess.setHasWaitingRequest(false);
                    currentProcess.setNextIORequest();
                }
            }
            //if there is an IO request scheduled for the current time and the cpu isn't waiting on another, begin it
            if (currentProcess.getNumRequests() > 0 && currentProcess.getCurrentIORequest() < (currentProcess.getNumRequests())) {
                if (clock == startTime + currentProcess.getIORequests().get(currentProcess.getCurrentIORequest())) {

                     if (!currentProcess.isWaitingForIO()) {
                        currentProcess.setCurrentIORequestStartTime(clock);
                        currentProcess.setWaitingForIO(true);
                        System.out.println("-> Process " + currentProcess.getPid() +
                                " on core " + id +
                                " waiting for IO #" + (currentProcess.getCurrentIORequest()+1));
                        currentProcess.setNextIORequest();
                    } else{
                        currentProcess.setWaitingIORequest(currentProcess.getCurrentIORequest());
                        currentProcess.setHasWaitingRequest(true);
                    }
                }
            }
        }

    }


    public int getId() {
        return id;
    }


}
