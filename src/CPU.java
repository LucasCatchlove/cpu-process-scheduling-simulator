public class CPU {

    private int numOfCores;
    private Core[] cores;
    static int clock;
    static int clockSpeed = 10;

    public CPU(int numOfCores) {
        cores = new Core[numOfCores];
        for (int i = 0; i < numOfCores; ++i)
            cores[i] = new Core(i);
    }



    public Core getNextFreeCore() {
        for (Core c : cores)
            if (!c.isAssignedProcess())
                return c;
        return null;
    }

    public int getNumFreeCores() {
        int count = 0;
        for (Core c : cores)
            if (!c.isAssignedProcess())
                count++;
        return count;

    }

    public void updateStateOfCores(int clock) {
        for(Core c : cores) {
            c.processIsComplete(clock);
            //c.processHasIO(clock);
        }

    }
}
