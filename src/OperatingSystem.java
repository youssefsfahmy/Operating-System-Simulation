import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class OperatingSystem {
	
	public static Queue<Process> readyQueue = new LinkedList<Process>();
	public static ArrayList<Thread> ProcessTable;
	public static Semaphore readSem = new Semaphore("Read");
	public static Semaphore writeSem = new Semaphore("Write");
	public static Semaphore printSem = new Semaphore("Print");
	public static Semaphore inputSem = new Semaphore("Input");

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
	public static void printText(String text) {

		System.out.println(text);
		
	}
	
	//4- take input
	
	@SuppressWarnings("unused")
	public static String TakeInput() {
		Scanner in= new Scanner(System.in);
		String data = in.nextLine();
		return data;
		
	}
	
	private static void createProcess(int processID){
		Process p = new Process(processID);
		ProcessTable.add(p);
		Process.setProcessState(p,ProcessState.Ready);
		readyQueue.add(p);
		int i = 0;
		int j = 0;// this is to cound (to the size of the table, to check all the processes are not alive if j = size of table then we have looped over the whole table
		
		//This is the scheduler: it runs by checking there is no (alive) processes in the process table, if so,  the scheduler then pops one process from the ready queue and starts it
		while(true){
			if(readyQueue.isEmpty() || ProcessTable.isEmpty()){
				break;
			}
			
			if(j==ProcessTable.size()){
				Process current = readyQueue.remove();
				
				if(current.isSuspended){
					current.resume(); // in the case of the process being blocked , it is resumed
				}else{
					current.start(); // else it has just been creaded, it is started
				}
				
				j=0;
			}
			Thread pcurrent = ProcessTable.get(i);
			if(pcurrent.isAlive()){
				j=0;
				while(pcurrent.isAlive()){
				}
			}
			j++;
			
			if(i==ProcessTable.size()-1){
				i=0;
			}
			else{
				i++;
			}
			
			
		}
		

	}
	
	public static void main(String[] args) {
   		ProcessTable = new ArrayList<Thread>();

		createProcess(1);
		createProcess(2);
		createProcess(3);
		createProcess(4);
		createProcess(5);
		
		

	}
}



