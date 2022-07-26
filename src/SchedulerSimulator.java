import java.io.*;
import java.util.List;
import java.util.Scanner;


public class SchedulerSimulator {


    private static Scheduler CurrentScheduler;


    public static void main(String[] args) {
        System.out.println("********* Schedule Simulator ********");
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter a time quantum value: ");
        //int timeQ = kb.nextInt();
        int timeQ = 2;
        try {
            FileProcessor.parseFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numOfCores = FileProcessor.getNumOfCores();
        List<Process> processes = FileProcessor.getProcesses();

        CPU Cpu = new CPU(numOfCores);

        //Scheduler FCFS = new FirstComeFirstServed(processes, Cpu);
        //FCFS.schedule();
        Scheduler SJF = new ShortestJobFirst(processes, Cpu);
        SJF.schedule();
    }

    public static Scheduler getCurrentScheduler() {
        return CurrentScheduler;
    }


}
