import java.util.Comparator;

class ProcessArrivalComparator implements Comparator<Process> {
    @Override
    public int compare(Process o1, Process o2) {
        if (o1.getArrivalTime() > o2.getArrivalTime()) return 1;
        else if (o1.getArrivalTime() < o2.getArrivalTime()) return -1;
        else return 0;
    }
}
