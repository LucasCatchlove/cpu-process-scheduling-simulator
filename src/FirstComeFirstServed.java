import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;

public class FirstComeFirstServed extends Scheduler {

    Queue<Process> readyQueue = new PriorityQueue<>(new ProcessArrivalComparator());
    Queue<Process> waitingQueue = new PriorityQueue<>(new ProcessArrivalComparator());


    public FirstComeFirstServed(List<Process> processes, CPU Cpu) {
        super(Cpu);
        readyQueue.addAll(processes);
    }

    public void schedule() {
        System.out.println("\n********** First Come First Served ********");

        while (CPU.clock < 150) {
            System.out.println("---------- @ time " + CPU.clock + " ----------");
            try {
                Thread.sleep(CPU.clockSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (CPU.clock > 0) Cpu.updateStateOfCores(CPU.clock);

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

class ProcessArrivalComparator implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        if (o1.getArrivalTime() > o2.getArrivalTime()) return 1;
        else if (o1.getArrivalTime() < o2.getArrivalTime()) return -1;
        else return 0;
    }
}





