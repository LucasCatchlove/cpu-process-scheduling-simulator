
public class Core {

    private boolean isAssignedProcess = false;
    private Process currentProcess;
    private int startTime;
    private int id;

    private Scheduler scheduler;
    private int timeElapsed = 0;

    public Core(int id) {
        this.id = id;
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


    public void removeCompletedProcess(int clock) {
        if ((currentProcess != null && clock >= startTime + currentProcess.getExecTime() + currentProcess.getNumRequests() * 2) && !currentProcess.isWaitingForIO()) {
            System.out.println("-> Process " + currentProcess.getPid() +
                    " on core " + id + " has completed");
            isAssignedProcess = false;
            currentProcess = null;
        }
    }


    public Process removeCompletedProcess(int clock, int timeQ) {
        if (currentProcess != null) {
            currentProcess.decrTimeRemaining();
            timeElapsed++;
            if (timeElapsed == timeQ) {
                System.out.println(currentProcess.getTimeRemaining());
                if(currentProcess.getTimeRemaining() == 0) {
                    System.out.println("-> Process " + currentProcess.getPid() +
                            " on core " + id + " has completed");
                }
                else {
                    currentProcess.decrTimeRemaining();
                    System.out.println("-> Process " + currentProcess.getPid() +
                            " on core " + id + " has used up allotted cpu time. Placing Process back in queue");
                    timeElapsed = 0;
                    return currentProcess;
                }
                timeElapsed = 0;
                isAssignedProcess = false;
                currentProcess = null;
            }
            else {

            }


        }
        return null;
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

                if (currentProcess.hasWaitingRequest()) {
                    System.out.println("-> Process " + currentProcess.getPid() +
                            " on core " + id +
                            " waiting for IO #" + (currentProcess.getCurrentIORequest() + 1) +
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
                                " waiting for IO #" + (currentProcess.getCurrentIORequest() + 1));
                        currentProcess.setNextIORequest();
                    } else {
                        currentProcess.setWaitingIORequest(currentProcess.getCurrentIORequest());
                        currentProcess.setHasWaitingRequest(true);
                    }
                }
            }
        }

    }

    public boolean isAssignedProcess() {
        return isAssignedProcess;
    }

    public int getId() {
        return id;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }


}
