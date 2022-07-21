import java.io.*;
import java.util.List;


public class SchedulerSimulator {


    private static int numOfCores;
    private static List<Process> processes;

    public static void main(String[] args) throws IOException {
        FileProcessor.parseFile("input.txt");

        //System.out.println(FileProcessor.getNumOfCores());
        numOfCores = FileProcessor.getNumOfCores();
        //System.out.println(FileProcessor.getProcesses());
        processes = FileProcessor.getProcesses();

        CPU Cpu = new CPU(numOfCores);








    }
}
