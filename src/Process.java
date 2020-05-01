import java.util.concurrent.Semaphore;

//import java.util.concurrent.Semaphore;


public class Process extends Thread {
	
	public volatile int processID;
    ProcessState status=ProcessState.New;
    public volatile int arrivalTime; 
    public volatile boolean resume;
    public volatile boolean isresuming;

    

	
	public Process(int m,int arrive) {
		processID = m;
		resume=false;
		isresuming=false;
		arrivalTime=arrive;
		
	}
	@Override
	public void run() {

		switch(processID)
		{
		case 1:process1();break;
		case 2:process2();break;
		case 3:process3();break;	
		case 4:process4();break;	
		case 5:process5();break;
		case 6:process3();break;
		}
		if(!OperatingSystem.stillAlive.isEmpty())
		OperatingSystem.stillAlive.remove(0);
	}
	

	
	private void process1() {
	    
		
		MutexSemaphore.semWait(OperatingSystem.printSemaphore, this);
		MutexSemaphore.semWait(OperatingSystem.readfileSemaphore, this);
		MutexSemaphore.semWait(OperatingSystem.takeinputSemaphore ,this);
		OperatingSystem.printText("Enter File Name: ");
		OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
		MutexSemaphore.semSignal(OperatingSystem.takeinputSemaphore, this);
		MutexSemaphore.semSignal(OperatingSystem.readfileSemaphore, this);
		MutexSemaphore.semSignal(OperatingSystem.printSemaphore, this);
	
		
		setProcessState(this,ProcessState.Terminated);
	
		}
	
	private void process2() {
		MutexSemaphore.semWait(OperatingSystem.printSemaphore, this);
		OperatingSystem.printText("Enter File Name: ");
		MutexSemaphore.semWait(OperatingSystem.takeinputSemaphore, this);
		String filename= OperatingSystem.TakeInput();
		MutexSemaphore.semSignal(OperatingSystem.takeinputSemaphore, this);
		MutexSemaphore.semSignal(OperatingSystem.printSemaphore, this);
		MutexSemaphore.semWait(OperatingSystem.printSemaphore, this);
		OperatingSystem.printText("Enter Data: ");
		MutexSemaphore.semWait(OperatingSystem.takeinputSemaphore, this);
		String data= OperatingSystem.TakeInput();
		MutexSemaphore.semSignal(OperatingSystem.takeinputSemaphore, this);
		MutexSemaphore.semSignal(OperatingSystem.printSemaphore, this);
		MutexSemaphore.semWait(OperatingSystem.writefileSemaphore, this);
		OperatingSystem.writefile(filename,data);
		MutexSemaphore.semSignal(OperatingSystem.writefileSemaphore, this);
		setProcessState(this,ProcessState.Terminated);
		
		}
	private void process3()  {
		int x=0;
		MutexSemaphore.semWait(OperatingSystem.printSemaphore, this);
		while (x<301)
			
		{ 
		
			OperatingSystem.printText(x+"\n");
			

			x++;
		}
		MutexSemaphore.semSignal(OperatingSystem.printSemaphore, this);
		setProcessState(this,ProcessState.Terminated);
		
		}
	
	private void process4()  {
	
		int x=500;
		MutexSemaphore.semWait(OperatingSystem.printSemaphore, this);
		while (x<1001)
		{
			
			OperatingSystem.printText(x+"\n");
			
			x++;
		}	
		MutexSemaphore.semSignal(OperatingSystem.printSemaphore, this);
		setProcessState(this,ProcessState.Terminated);
	
		}
	private void process5() {
		MutexSemaphore.semWait(OperatingSystem.printSemaphore, this);
		OperatingSystem.printText("Enter LowerBound: ");
	
		MutexSemaphore.semWait(OperatingSystem.takeinputSemaphore,this);
		String lower= OperatingSystem.TakeInput();
		MutexSemaphore.semSignal(OperatingSystem.takeinputSemaphore, this);
		MutexSemaphore.semSignal(OperatingSystem.printSemaphore, this);
		MutexSemaphore.semWait(OperatingSystem.printSemaphore, this);
		OperatingSystem.printText("Enter UpperBound: ");
	
		MutexSemaphore.semWait(OperatingSystem.takeinputSemaphore, this);
		String upper= OperatingSystem.TakeInput();
		MutexSemaphore.semSignal(OperatingSystem.takeinputSemaphore, this);
		MutexSemaphore.semSignal(OperatingSystem.printSemaphore, this);
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			
			data+=lowernbr++ +"\n";
		}
		MutexSemaphore.semWait(OperatingSystem.writefileSemaphore, this);
		OperatingSystem.writefile("P5.txt", data);
		MutexSemaphore.semSignal(OperatingSystem.writefileSemaphore, this);
		setProcessState(this,ProcessState.Terminated);
		
	}
	
	 public static void setProcessState(Process p, ProcessState s) {
		 p.status=s;
		 if (s == ProcessState.Terminated)
		 {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		 }
	}
	 
	 public static ProcessState getProcessState(Process p) {
		 return p.status;
	}
	
	 
	 
	 
	 
	 
	 
	 
	 
	
}
	 
	 
	 














 
     
     











