import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class test {
	
	public static Queue<String> readyQueue = new LinkedList<String>();
	public static ArrayList<String> ProcessTable = new ArrayList<>();
	
	private static void createProcess(String processID){

		ProcessTable.add(processID);
		readyQueue.add(processID);
		int i = 0;
		int j = 0;
		while(true){
			System.out.println("i am at" + ProcessTable.get(i));
			if(readyQueue.isEmpty()){
				break;
			}
			
			if(j==ProcessTable.size()){
				String current = readyQueue.remove();
				System.out.println("STARTING" + current);
				j=0;
			}
			
			if(ProcessTable.get(i)=="a"){
				j=0;
			}
			
			j++;
			
			if(i==ProcessTable.size()-1){
				i=0;
			}
			else{
				i++;
			}
			
			
		}	
		
		System.out.println("OUT");
	}
	public static void main (String [] args){
		ProcessTable.add("AAAA1");
		ProcessTable.add("AAAA2");
		ProcessTable.add("AAAA3");
		ProcessTable.add("AAAA4");
		ProcessTable.add("AAAA5");
		
		readyQueue.add("BBB1");
		readyQueue.add("BBB2");
		readyQueue.add("BBB3");
		readyQueue.add("BBB4");
		readyQueue.add("BBB5");
		
		createProcess("new");
		
	}
}
