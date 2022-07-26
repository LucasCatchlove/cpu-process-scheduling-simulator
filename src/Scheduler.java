import java.util.List;

abstract class Scheduler {

    CPU Cpu;

    public Scheduler(CPU Cpu) {
        this.Cpu = Cpu;
    }

    public abstract void schedule();
}
