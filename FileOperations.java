import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



public class FileOperations {

	// A single file path argument taking method for file reading
	// it returns array contains lines as elements 
	public static String[] readFile(String path) {

		try {
			int i=0;
			int length=Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[length];
				for (String line : Files.readAllLines(Paths.get(path))) {
					results[i++] = line;
				}
			return results;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeToFile(String description, Object datastructure, String filename, Boolean isnotoverride) {
		try {
		File f = new File(filename);
		FileWriter filewriter = new FileWriter(f, isnotoverride);
		filewriter.write(description);
		filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void conductCommands(String commandfilepath) {				// ***Operating methods within a single method***
		
		String[] tmpcommands = readFile(commandfilepath);
		String[][] commands = new String[tmpcommands.length][];
		for(int i=0; i<tmpcommands.length; i++) {			
			commands[i] = tmpcommands[i].split(" ");
		}
			
		String[] tmpArray = FileOperations.readFile("stack.txt")[0].split(" ");
		int[] tmpIntArray = new int[tmpArray.length]; 
		for (int i = 0; i < tmpIntArray.length; i++){
			tmpIntArray[i] = Integer.parseInt(tmpArray[i]);
		}
		Stack stack = new Stack();			//initializing stack named stack 
		for (int i = tmpIntArray.length - 1; i >= 0; i--) {
			stack.push(tmpIntArray[i]);
		}
		
		String[] tmpQueue = FileOperations.readFile("queue.txt")[0].split(" ");
		int[] tmpIntQueue = new int[tmpQueue.length]; 
		for (int i = 0; i < tmpIntQueue.length; i++){
			tmpIntQueue[i] = Integer.parseInt(tmpQueue[i]);
		}
		Queue queue = new Queue();			//initializing queue named queue 
		for (int i=0; i <= tmpIntQueue.length - 1; i++) {
			queue.enqueue(tmpIntQueue[i]);
		}
		
		
		for (String[] line: commands) {		// operating commands one by one
			if (line[1].equalsIgnoreCase("removeGreater")) {
				if (line[0].equalsIgnoreCase("Q")) {
					Queue tmpqueue = new Queue();
					while (!queue.isEmpty()) {
						int j = queue.dequeue();
						if (j>Integer.parseInt(line[2])) {
							continue;
						}
						else {
							tmpqueue.enqueue(j);
						}
					}
					while (!tmpqueue.isEmpty()) {
						queue.enqueue(tmpqueue.dequeue());
					}

					writeToFile("After removeGreater " + line[2] + "\n" + queue.myqueue.toString()
								.replace("[","").replace("]","").replace(",","") + "\n", queue, "queueOut.txt", true);	
					
				}
				else {
					Stack tmpstack = new Stack();
					while (!stack.isEmpty()) {
						int j = stack.pop();
						if (j>Integer.parseInt(line[2])) {
							continue;
						}
						else {
							tmpstack.push(j);
						}
					}
					while (!tmpstack.isEmpty()) {
						stack.push(tmpstack.pop());
					}
					writeToFile("After removeGreater " + line[2] + "\n" + stack.mystack.toString()
					.replace("[","").replace("]","").replace(",","") + "\n", stack, "stackOut.txt", true);
				}
			}
			else if (line[1].equalsIgnoreCase("calculateDistance")) {
				if (line[0].equalsIgnoreCase("Q")) {
					Queue initialQueue = new Queue();
					Queue refillqueue = new Queue();
					int tmpnum = 0;
					while (!queue.isEmpty()) {
						tmpnum = queue.dequeue();
						initialQueue.enqueue(tmpnum);
						refillqueue.enqueue(tmpnum);
					}
					ArrayList<Integer> tmpal = new ArrayList<Integer>();
					int totalDistance = 0;
					while (!initialQueue.isEmpty()) {
						tmpal.add(initialQueue.dequeue());
					}
					for(int i=0;i<tmpal.size()-1;i++) {
						for (int j=i+1;j<tmpal.size();j++) {
							totalDistance += Math.abs((tmpal.get(i) - tmpal.get(j)));
						}
					}
					while (!tmpal.isEmpty()) {
						initialQueue.enqueue(tmpal.remove(0));
					}
					while (!refillqueue.isEmpty()) {
						queue.enqueue(refillqueue.dequeue());
					}
				writeToFile("After calculateDistance \n" + "Total distance=" + totalDistance + "\n", queue, "queueOut.txt", true);
				}
				else {
					
					ArrayList<Integer> tmpal = new ArrayList<Integer>();
					int totalDistance = 0;
					while (!stack.isEmpty()) {
						tmpal.add(stack.pop());
					}
					for(int i=0;i<tmpal.size()-1;i++) {
						for (int j=i+1;j<tmpal.size();j++) {
							totalDistance += Math.abs((tmpal.get(i) - tmpal.get(j)));
						}
					}
				while (!tmpal.isEmpty()) {
					stack.push(tmpal.remove(tmpal.size()-1));
				}
				writeToFile("After calculateDistance \n" + "Total distance=" + totalDistance + "\n", stack, "stackOut.txt", true);
				}
			}
			
			else if (line[1].equalsIgnoreCase("addOrRemove")) {
				if (line[0].equalsIgnoreCase("Q")) {
					if (Integer.parseInt(line[2]) < 0) {
						for (int i=0;i<Math.abs(Integer.parseInt(line[2]));i++) {
							queue.dequeue();
						}
					}
					else {
						for (int i=0;i<Integer.parseInt(line[2]);i++) {
							queue.enqueue((int)(50*Math.random()));
						}
					}
					writeToFile("After addOrRemove " + line[2] + "\n" + queue.myqueue.toString()
					.replace("[","").replace("]","").replace(",","") + "\n", queue, "queueOut.txt", true);
				}
				else {
					if (Integer.parseInt(line[2]) < 0) {
						for (int i=0;i<Math.abs(Integer.parseInt(line[2]));i++) {
							stack.pop();
						}
					}
					else {
						for (int i=0;i<Integer.parseInt(line[2]);i++) {
							stack.push((int)(50*Math.random()+1));
						}
					}
					writeToFile("After addOrRemove " + line[2] + "\n" + stack.mystack.toString()
					.replace("[","").replace("]","").replace(",","") + "\n", stack, "stackOut.txt", true);
				}
			}
		
			else if (line[1].equalsIgnoreCase("reverse")) {
				if (line[0].equalsIgnoreCase("Q")) {
					Stack tmpstack= new Stack();
					Queue tmpqueue = new Queue();					  
					for (int i=0;i<Integer.parseInt(line[2]);i++) {
						tmpstack.push(queue.dequeue());
					}
					while (!queue.isEmpty()) {
						tmpqueue.enqueue(queue.dequeue());
					}
					while (!tmpstack.isEmpty()) {
						queue.enqueue(tmpstack.pop());
					}
					while (!tmpqueue.isEmpty()) {
						queue.enqueue(tmpqueue.dequeue());
					}
					writeToFile("After reverse " + line[2] + "\n" + queue.myqueue.toString()
					.replace("[","").replace("]","").replace(",","") + "\n", queue, "queueOut.txt", true);
				}
				else {
					Queue tmpqueue= new Queue();
					for (int i=0;i<(Integer.parseInt(line[2]));i++) {
						tmpqueue.enqueue(stack.pop());
					}
					while (!tmpqueue.isEmpty())  {
						stack.push(tmpqueue.dequeue());
					}
					writeToFile("After reverse " + line[2] + "\n" + stack.mystack.toString()
					.replace("[","").replace("]","").replace(",","") + "\n", stack, "stackOut.txt", true);
				}
			}
			
			else if (line[1].equalsIgnoreCase("sortElements")) {
				if (line[0].equalsIgnoreCase("Q")) {
					ArrayList<Integer> tmpal = new ArrayList<Integer>();
					while (!queue.isEmpty()) {
						tmpal.add(queue.dequeue());
					}
					Collections.sort(tmpal);
					while (!tmpal.isEmpty()) {
						queue.enqueue(tmpal.remove(0));
					}
					writeToFile("After sortElements " + "\n" + queue.myqueue.toString()
					.replace("[","").replace("]","").replace(",","") + "\n", queue, "queueOut.txt", true);
				}
				else {
					ArrayList<Integer> tmpal = new ArrayList<Integer>();
					while (!stack.isEmpty()) {
						tmpal.add(stack.pop());
					}
					Collections.sort(tmpal);
					while (!tmpal.isEmpty()) {
						stack.push(tmpal.remove(tmpal.size()-1));
					}
					writeToFile("After sortElements " + "\n" + stack.mystack.toString()
					.replace("[","").replace("]","").replace(",","") + "\n", stack, "stackOut.txt", true);
				}
			}
			
			else if (line[1].equalsIgnoreCase("distinctElements")) {
				if (line[0].equalsIgnoreCase("Q")) {
				Set<Integer> tmpset = new HashSet<Integer>();
				int tmpnum = 0;
				Queue tmpqueue = new Queue();
				
				while (!queue.isEmpty()) {
					tmpnum = queue.dequeue();
					tmpset.add(tmpnum);
					tmpqueue.enqueue(tmpnum);
				}
				while (!tmpqueue.isEmpty()) {
					queue.enqueue(tmpqueue.dequeue());
				}
				writeToFile("After distinctElements \n" + "Total distinct element=" + tmpset.size() + "\n", queue, "queueOut.txt", true);
				}
				else {
					Set<Integer> tmpset = new HashSet<Integer>();
					int tmpnum = 0;
					Stack tmpstack = new Stack();
					while (!stack.isEmpty()) {
						tmpnum = stack.pop();
						tmpset.add(tmpnum);
						tmpstack.push(tmpnum);
					}
					while (!tmpstack.isEmpty()) {
						stack.push(tmpstack.pop());
					}
					writeToFile("After distinctElements \n" + "Total distinct element=" + tmpset.size() + "\n", stack, "stackOut.txt", true);
					}
				}
		}
		writeToFile(stack.mystack.toString().replace("[","").replace("]","").replace(",",""), stack, "stack.txt", false);
		writeToFile(queue.myqueue.toString().replace("[","").replace("]","").replace(",",""), queue, "queue.txt", false);
	}	
}
