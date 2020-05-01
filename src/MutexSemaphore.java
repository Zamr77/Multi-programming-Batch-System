import java.util.LinkedList;
import java.util.Queue;

public class MutexSemaphore {
	public volatile Queue<Process> blockingQueue;
	public volatile int value;
	public volatile int ownerID;
	

	public MutexSemaphore() {
		blockingQueue = new LinkedList<Process>();
		value = 1;

	}

	
	public static void semWait(MutexSemaphore s, Process p) {
		if (s.value == 1) {

			s.ownerID = p.processID;
			s.value = 0;

		} else {
			s.blockingQueue.add(p);
     

			p.isresuming = false;
			p.setProcessState(p, ProcessState.Waiting);
			while (!(p.isresuming));

		}
	}

	@SuppressWarnings("deprecation")
	public static void semSignal(MutexSemaphore s, Process p) {
		if (s.ownerID == p.processID) {
			if (s.blockingQueue.isEmpty()) {


				s.value = 1;
				

			} else {
				Process z = s.blockingQueue.remove();


				s.ownerID = z.processID;
				z.resume=true;
				z.setProcessState(z, ProcessState.Ready);
			     OperatingSystem.ReadyQueue.add(z);


				
			}
		}

	}
}
