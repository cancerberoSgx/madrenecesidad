package org.sgx.madrenecesidad.client.util.syncqueue;

public abstract class Task {
SyncQueue syncQueue; 

public void setSyncQueue(SyncQueue syncQueue) {
	this.syncQueue = syncQueue;
}

public SyncQueue getSyncQueue() {
	return syncQueue;
}
/**
 * called from user code to end this task and continue with the next on the parent Synqueue
 */
public void endTask() {
	syncQueue.endTask(this); 
}
public abstract void run();
}
