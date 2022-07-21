
public class Core implements Runnable {

    private boolean isAssignedProcess = false;
    private Process currentProcess;


    public void addProcess(Process p) {
        currentProcess = p;
        isAssignedProcess = !isAssignedProcess;
    }
    @Override
    public void run() {
        while(SchedulerSimulator.getCurrentScheduler().processesRemaining() && !isAssignedProcess) {
            try {
                Thread.sleep(currentProcess.getExecTime());
                isAssignedProcess = !isAssignedProcess;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
