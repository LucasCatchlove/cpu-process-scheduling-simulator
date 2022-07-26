import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    private static int numOfCores;
    private static final List<Process> processes = new ArrayList<Process>();

    public static void parseFile(String fileName) throws IOException {
        try {
            File file = new File(fileName);
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null)
                if (line.startsWith("numOfCPUs"))
                    numOfCores = parseNumOfCPUs(line);
                else if(line.startsWith("p"))
                    processes.add(parseProcess(line));
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }

    private static int parseNumOfCPUs(String line) {
        String CPUs = line.split("[ \t]+")[1];
        return Integer.parseInt(CPUs);
    }

    private static Process parseProcess(String line) {
        String[] processStr = line.split("[ \t]+");

        String pid = processStr[0];
        int arrivalTime = Integer.parseInt(processStr[1]);
        int execTime = Integer.parseInt(processStr[2]);

        String[] IORequestsStr = Arrays.copyOfRange(processStr, 3, processStr.length);
        int[] IORequests = new int[IORequestsStr.length];

        for(int i = 0; i < IORequestsStr.length; ++i)
            IORequests[i] = Integer.parseInt(IORequestsStr[i]);

        return new Process(pid, arrivalTime, execTime, IORequests);
    }

    public static int getNumOfCores() {
        return numOfCores;
    }

    public static List<Process> getProcesses() {
        return processes;
    }


}




