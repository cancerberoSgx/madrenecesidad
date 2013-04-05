package org.sgx.madrenecesidad.client.util.syncqueue;

public class SyncQueueTest {
public static void main(String[] args) {
	SyncQueue sq = new SyncQueue();
	sq.append(new Task() {		
		@Override
		public void run() {
			System.out.println("1");
			endTask(); 
		}
	}); 
	sq.append(new Task() {		
		@Override
		public void run() {
			System.out.println("2");
			endTask(); 
		}
	}); 
	sq.append(new Task() {		
		@Override
		public void run() {
			System.out.println("3");
			endTask(); 
		}
	}); 
	sq.run(); 
}
}
