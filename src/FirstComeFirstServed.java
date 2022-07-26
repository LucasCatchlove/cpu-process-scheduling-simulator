import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;

public class FirstComeFirstServed extends Scheduler {
    int numProcesses;
    Queue<Process> readyQueue = new PriorityQueue<>(new ProcessComparator());
    Queue<Process> waitingQueue = new PriorityQueue<>(new ProcessComparator());


    public FirstComeFirstServed(List<Process> processes, CPU Cpu) {
        super(Cpu);
        numProcesses = processes.size();
        readyQueue.addAll(processes);
    }

    public void schedule() {
        while (CPU.clock < 200) {
            System.out.println("---------- @ time " + CPU.clock + " ----------");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (CPU.clock > 0) Cpu.updateStateOfCores(CPU.clock);

            while (!readyQueue.isEmpty() && readyQueue.peek().getArrivalTime() == CPU.clock) {

                if (Cpu.getNextFreeCore() != null) {
                    if (!waitingQueue.isEmpty())
                        Cpu.getNextFreeCore().addProcess(waitingQueue.remove(), CPU.clock);
                    else
                        Cpu.getNextFreeCore().addProcess(readyQueue.remove(), CPU.clock);
                }
                else waitingQueue.add(readyQueue.remove());

            }


            CPU.clock++;
        }
    }
}

class ProcessComparator implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        if (o1.getArrivalTime() > o2.getArrivalTime()) return 1;
        else if (o1.getArrivalTime() < o2.getArrivalTime()) return -1;
        else return 0;
    }
}





