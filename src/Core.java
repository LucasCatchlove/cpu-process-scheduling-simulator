
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

    }

    public void processHasIO(int clock) {
        if (currentProcess != null) {
            if (currentProcess.getNumRequests() >= 0 && currentProcess.getCurrentIORequest() < (currentProcess.getNumRequests())) {
                System.out.println("current curr: " + currentProcess.getCurrentIORequest());
                if (clock == startTime + currentProcess.getIORequests().get(currentProcess.getCurrentIORequest())) {

                     if (!currentProcess.isWaitingForIO()) {
                        currentProcess.setCurrentIORequestStartTime(clock);
                        currentProcess.setWaitingForIO(true);
                        System.out.println("-> Process " + currentProcess.getPid() + " on core " + id + " waiting for IO");
                        currentProcess.setNextIORequest();
                        System.out.println(currentProcess.getCurrentIORequest());
                    } else{
                        currentProcess.setWaitingIORequest(currentProcess.getCurrentIORequest());
                        currentProcess.setHasWaitingRequest(true);
                    }
                }
            }
            //cancelling IO
            if (clock == currentProcess.getCurrentIORequestStartTime() + 2) {

                currentProcess.setWaitingForIO(false);

                System.out.println("-> IO " + (currentProcess.getCurrentIORequest()) +
                        "/" + currentProcess.getNumRequests() +
                        " started at time " + currentProcess.getCurrentIORequestStartTime() +
                        " on Process " + currentProcess.getPid() +
                        " on core " + id + " has completed");


                if(currentProcess.hasWaitingRequest()) {
                    System.out.println("-> Process " + currentProcess.getPid() + " on core " + id + " waiting for IO");
                    currentProcess.setCurrentIORequestStartTime(clock);
                    currentProcess.setWaitingForIO(true);
                    currentProcess.setHasWaitingRequest(false);
                    currentProcess.setNextIORequest();
                }

            }
        }

    }


    public int getId() {
        return id;
    }


}
