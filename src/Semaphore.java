import java.util.LinkedList;
import java.util.Queue;


public class Semaphore {
	String name;

	
	Queue<Process> blocked = new LinkedList<Process>();
	boolean semFlag=true;
	
	
	public Semaphore(String name){
		this.name=name;
	}
	
	
	@SuppressWarnings("deprecation")
	public void semWait(Process p){
		System.out.println("A SEM WAIT: (" + this.name + ")  was called here"); // these are just to indicate where the sem wait was called
		if(semFlag){
			this.semFlag=false;
		}
		else{
			p.stop();
			Process.setProcessState(p, ProcessState.Waiting);
			p.isSuspended=true;
			this.blocked.add(p);
		}
	}
	
	
	public void semSignal() {
		System.out.println("A SEM POST: (" + this.name + ")  was called here");// these are just to indicate where the sem post was called

		if (this.blocked.isEmpty()){
			this.semFlag=true;
		}
		else {
			Process p=this.blocked.remove();
			Process.setProcessState(p, ProcessState.Ready);
			OperatingSystem.readyQueue.add(p);
			

		/* remove a process P from s.queue */
		/* place process P on ready list */
		}
		}
	

}
