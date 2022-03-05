import java.util.ArrayList;

public class Queue {
	
	
	public Queue() {
		myqueue = new ArrayList<Integer>();
	}


	public ArrayList<Integer> myqueue;

	//  ***Methods peculiar to Queue***
	public void enqueue(int number) {               // enqueue method
		myqueue.add(number);
	}
	
	public int dequeue() {							// dequeue method
		return myqueue.remove(0);
	}
	
	public int peek() { 							// peek method
		return myqueue.get(0);
	}
	
    public boolean isEmpty() {						// isEmpty method
        return myqueue.isEmpty();
    }
	
}
