import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;

public class ShortestJobFirst extends Scheduler {

    Queue<Process> readyQueue = new PriorityQueue<>((o1, o2) -> {
        if (o1.getArrivalTime() > o2.getArrivalTime()) return 1;
        else if (o1.getArrivalTime() < o2.getArrivalTime()) return -1;
        else if(o1.getArrivalTime() == o2.getArrivalTime())
            if (o1.getExecTime() > o2.getExecTime()) return 1;
            else if (o1.getExecTime() < o2.getExecTime()) return -1;
        return 0;
    });
    Queue<Process> waitingQueue = new PriorityQueue<>((o1, o2) -> {
        if (o1.getExecTime() > o2.getExecTime()) return 1;
        else if (o1.getExecTime() < o2.getExecTime()) return -1;
        else return 0;
    });


    public ShortestJobFirst(List<Process> processes, CPU Cpu) {
        super(Cpu);
        readyQueue.addAll(processes);

//        waitingQueue.addAll(processes);
//        for(int i =0; i < readyQueue.size(); ++i)
//            System.out.print(waitingQueue.remove());
//
//        for(int i =0; i < readyQueue.size(); ++i)
//            System.out.print(readyQueue.remove());




    }

    public void schedule() {
        System.out.println("\n********** Shortest Job First ********");

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







