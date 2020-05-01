import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import com.sun.javafx.collections.SortableList;
import com.sun.swing.internal.plaf.basic.resources.basic;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory.Zephyr;

import sun.net.www.content.text.plain;
import sun.print.resources.serviceui;


public class OperatingSystem {
	
	public static ArrayList<Thread> ProcessTable;
	public static volatile Queue<Process> ReadyQueue;
	public   static volatile MutexSemaphore printSemaphore=new MutexSemaphore();
	public  static  volatile MutexSemaphore readfileSemaphore=new MutexSemaphore();
	public  static volatile MutexSemaphore writefileSemaphore=new MutexSemaphore();
	public  static volatile MutexSemaphore takeinputSemaphore=new MutexSemaphore();
	public static volatile ArrayList<Boolean> stillAlive=new ArrayList<Boolean>();
	public static volatile ArrayList<ArrayList<Object>> arrTime=new ArrayList<ArrayList<Object>>();

	
   
//	public static int activeProcess= 0;
	//system calls:
	// 1- Read from File
	@SuppressWarnings("unused")
	public static String readFile(String name) {
	  
		String Data="";
		File file = new File(name);
	 try {
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine())
		{
			Data+= scan.nextLine()+"\n";
		}
		scan.close();
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	}

		return Data;
	}
	
	// 2- Write into file
	@SuppressWarnings("unused")
	public static void writefile(String name, String data) {
		
		try
		{
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		

	}
	//3- print to console
	@SuppressWarnings("unused")
	public static void printText(String text)  {
      
       
		System.out.println(text);
		

	}
	
	//4- take input
	
	@SuppressWarnings("unused")
	public static String TakeInput() {
	
		Scanner in= new Scanner(System.in);
		String data = in.nextLine();
	
		return data;
	
	}
	
	@SuppressWarnings("deprecation")
	private static void createProcess(int processID,int arrivaltime){
		Process p = new Process(processID,arrivaltime);
		ProcessTable.add(p);
		stillAlive.add(p.isAlive());
		ArrayList<Object>z=new ArrayList<Object>();
		z.add(p);
		z.add(p.arrivalTime);
		arrTime.add(z);
		Process.setProcessState(p,ProcessState.Ready);
		
		
	}
	private static void FCFStoexecute() {
		sort(arrTime);
		while(!arrTime.isEmpty()) {
			ArrayList<Object>r=arrTime.remove(0);
			ReadyQueue.add((Process) r.get(0));
		}
		while(!stillAlive.isEmpty()) {
		
		
		while(!ReadyQueue.isEmpty() ) {
			Process p=ReadyQueue.remove();

			execute(p);
			
				
			
		}
	}
	}
	

	

	private static void execute(Process p) {
		if(p.resume) {
			p.isresuming=true;
		p.setProcessState(p, ProcessState.Running);
		}
		else {
		p.start();
		p.setProcessState(p, ProcessState.Running);
		}
		while(p.isAlive());
	
		
		
	}
	
	public static void sort(ArrayList<ArrayList<Object>> a) {
	for(int i=0;i<a.size();i++)
    {
        int j = i;

        //i is not the first element
        while(j>0)
        {
            //not in order
            if((int)a.get(j-1).get(1) >(int) a.get(j).get(1))
            {
                //swapping
                Object temp = a.get(j-1);
                a.set(j-1, a.get(j));
                a.set(j, (ArrayList<Object>) temp);
            }
            //in order
            else
            {
                break;
            }
            j--;
        }
}
	}
	

	public static void main(String[] args) {
   		ProcessTable = new ArrayList<Thread>();
        ReadyQueue=new LinkedList<Process>();

		createProcess(1,2);
		createProcess(2,3);
		createProcess(3,1);
		createProcess(4,6);
		createProcess(5,7);
	   FCFStoexecute();

	}

	
}



