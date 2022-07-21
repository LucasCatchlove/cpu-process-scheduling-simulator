public class CPU {

    private int numOfCores;
    private Core[] cores;
    public CPU(int numOfCores) {
        cores = new Core[numOfCores];
        for(int i = 0; i < numOfCores; ++i)
            cores[i] = new Core();
    }
}
