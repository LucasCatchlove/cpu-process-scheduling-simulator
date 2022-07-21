import java.io.*;
import java.util.List;


public class SchedulerSimulator {


    private static int numOfCores;
    private static List<Process> processes;
    private static Scheduler CurrentScheduler;

    public static void main(String[] args) throws IOException {
        FileProcessor.parseFile("input.txt");

        //System.out.println(FileProcessor.getNumOfCores());
        numOfCores = FileProcessor.getNumOfCores();
        //System.out.println(FileProcessor.getProcesses());
        processes = FileProcessor.getProcesses();

        CPU Cpu = new CPU(numOfCores);
        Cpu.initializeCPU();

        //add algo selection here




    }

    public static Scheduler getCurrentScheduler() {
        return CurrentScheduler;
    }
}
