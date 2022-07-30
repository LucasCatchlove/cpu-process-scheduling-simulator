import java.io.*;
import java.util.List;
import java.util.Scanner;

public class SchedulerSimulator {

    public static void main(String[] args) {
        System.out.println("********* Schedule Simulator ********");
        Scanner kb = new Scanner(System.in);
        //System.out.print("Enter a time quantum value: ");
        //int timeQ = kb.nextInt();
        int timeQ = 4;
        try {
            FileProcessor.parseFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numOfCores = FileProcessor.getNumOfCores();
        List<Process> processes = FileProcessor.getProcesses();


        CPU Cpu = new CPU(numOfCores);

        Scheduler FCFS = new FirstComeFirstServed(processes, Cpu);
        FCFS.schedule();
        Scheduler SJF = new ShortestJobFirst(processes, Cpu);
        SJF.schedule();
        Scheduler RR = new RoundRobin(processes, Cpu, timeQ);
        RR.schedule();
        for(Core c : Cpu.getCores()) {
            System.out.println("Core " + c.getId() + " was used for " + ((c.getCpuUtilization() / (float)CPU.clock) * 100) + "%");

        }
    }
}
