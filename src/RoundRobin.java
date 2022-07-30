import java.util.*;

public class RoundRobin extends Scheduler {

    Queue<Process> arrivalQueue = new LinkedList<>();
    Queue<Process> waitingQueue = new LinkedList<>();
    Queue<Process> readyQueue = new LinkedList<>();
    private final int timeQ;


    public RoundRobin(List<Process> processes, CPU Cpu, int timeQ) {
        super(Cpu);
        processes.sort(new ProcessArrivalComparator());
        arrivalQueue.addAll(processes);
        System.out.println("fd" + arrivalQueue);
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
                    if(incomplete != null) {
                        readyQueue.add(incomplete);
                    }
                }
            }
            while (!waitingQueue.isEmpty()) {
                if (Cpu.getNextFreeCore() != null)
                    Cpu.getNextFreeCore().addProcess(waitingQueue.remove(), CPU.clock);
                else break;
            }

            while (!readyQueue.isEmpty()) {
                if (Cpu.getNextFreeCore() != null) {
                    Cpu.getNextFreeCore().addProcess(readyQueue.remove(), CPU.clock);
                } else {
                    Process delayedProcess = readyQueue.remove();
                    delayedProcess.setDelay(CPU.clock-delayedProcess.getArrivalTime());
                    waitingQueue.add(delayedProcess);
                }
            }
            while (!arrivalQueue.isEmpty() && arrivalQueue.peek().getArrivalTime() + arrivalQueue.peek().getDelay()  == CPU.clock) {
                if (Cpu.getNextFreeCore() != null) {
                    Cpu.getNextFreeCore().addProcess(arrivalQueue.remove(), CPU.clock);
                } else {
                    Process delayedProcess = arrivalQueue.remove();
                    delayedProcess.setDelay(CPU.clock-delayedProcess.getArrivalTime());
                    waitingQueue.add(delayedProcess);
                }
            }
            CPU.clock++;
            if(readyQueue.isEmpty() && waitingQueue.isEmpty() && arrivalQueue.isEmpty()) {
                break;
            }
        }
    }

}


