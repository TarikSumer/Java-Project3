import java.util.ArrayList;

public class Stack {
	
	
	public Stack() {
		mystack = new ArrayList<Integer>();
	}
  
	
	public ArrayList<Integer> mystack;
	
	//   ***Methods peculiar to Stack***
	public void push(int number) {              // push method
		mystack.add(0, number);
	}
	
	public int pop() {							// pop method
		return mystack.remove(0);
	}
	
	public int peek() { 						// peek method
		return mystack.get(0);
	}
	
    public boolean isEmpty() {					// isEmpty method
        return mystack.isEmpty();
    }

}
