import java.util.*;

public class RoundRobin extends Scheduler {

    Queue<Process> readyQueue = new LinkedList<>();
    Queue<Process> waitingQueue = new LinkedList<>();
    private final int timeQ;


    public RoundRobin(List<Process> processes, CPU Cpu, int timeQ) {
        super(Cpu);
        processes.sort(new ProcessArrivalComparator());
        readyQueue.addAll(processes);
        this.timeQ = timeQ;
    }

    public void schedule() {
        System.out.println("\n********** Round Robin Q = " + timeQ +" ********");

        while (CPU.clock < CPU.timeLimit) {
            System.out.println("---------- @ time " + CPU.clock + " ----------");
            try {
                Thread.sleep(CPU.clockSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //manages print statements as well to signal events like IO completion
            if (CPU.clock > 0) {

                for(Core c : Cpu.getCores()) {
                    //c.IOScheduler(CPU.clock);
                    Process incomplete = c.removeCompletedProcess(CPU.clock, timeQ);
                    if(incomplete != null)
                        waitingQueue.add(incomplete);
                }

            }
            while (!waitingQueue.isEmpty())
                if (Cpu.getNextFreeCore() != null)
                    Cpu.getNextFreeCore().addProcess(waitingQueue.remove(), CPU.clock);
                else break;

            while (!readyQueue.isEmpty() && readyQueue.peek().getArrivalTime() == CPU.clock) {
                if (Cpu.getNextFreeCore() != null) {
                    Cpu.getNextFreeCore().addProcess(readyQueue.remove(), CPU.clock);
                } else {
                    Process delayedProcess = readyQueue.remove();
                    delayedProcess.setDelay(CPU.clock-delayedProcess.getArrivalTime());
                    waitingQueue.add(delayedProcess);
                }
            }
            CPU.clock++;
        }
    }

}


