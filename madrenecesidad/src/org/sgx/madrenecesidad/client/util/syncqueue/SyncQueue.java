package org.sgx.madrenecesidad.client.util.syncqueue;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.Timer;

public class SyncQueue {
List<Task> tasks; 
int delayBetweenTask = 1000; 

public SyncQueue() {
	tasks = new LinkedList<Task>(); 
}
public void run() {
	this.tasks.get(0).run(); 
}

public void append(Task task) {
	task.setSyncQueue(this); 
	this.tasks.add(task); 
}

public int getDelayBetweenTask() {
	return delayBetweenTask;
}
public void setDelayBetweenTask(int delayBetweenTask) {
	this.delayBetweenTask = delayBetweenTask;
}
public void endTask(Task task) {
	this.tasks.remove(0); 
	if(this.tasks.size()>0) {
		new Timer() {				
			@Override
			public void run() {
				if(tasks.size()>0)
					tasks.get(0).run();
			}
		}.schedule(delayBetweenTask);
	}
}
}
