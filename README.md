# cpu-process-scheduling-simulator
A tool to simulate process scheduling by the cpu using common algorithms like round-robin and first-come-first-serve. 

I/O operations are simulated as well.

**Usage:**

A user must provide an input file denoting the number of cores as well as the processes currently running on the simulated machine. 

The format of the file is as follows: 

```
numOfCPUs:	2

// list of processes to be scheduled
// processID		arrivalTime		totalExecTime		IO_RequestAtTime 	IO_RequestAtTime		IO_RequestAtTime		...
p0	0	10	2	5	8
p1	2	3
p2	10	7	1	2	3

```

Written in java using common object-oriented design patterns. 

