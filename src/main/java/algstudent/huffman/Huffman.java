package algstudent.huffman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Huffman {  
	private List<String> elements; //stores the elements to be compressed
	private List<Integer> frequencies; //stores how many of each elements we have
	private HuffmanNode node; //stores the nodes of the tree. Eventually it will only store the root node
	private Map<String, String> codes; //map to store the codes for testing purposes
	
    public static void main(String[] args) { 
    	if (args.length > 0) {
        	Huffman huffman = new Huffman(args[0]);
        	huffman.run();
        	huffman.getCodes();
    	}
    	else {
    		System.out.println("The first argument of the program should be the name of the file with the input");
    	}
    } 
    
    public Huffman(String fileName) {
    	elements = new ArrayList<String>();
    	frequencies = new ArrayList<Integer>();
    	codes = new HashMap<String, String>();
    	
    	try (Stream<String> stream = Files.lines(Paths.get(fileName))) { //Streams in Java 8
    		stream.map(line -> line.split(" "))
    			  .forEach(values -> {
    					elements.add(values[0]);
    					frequencies.add(Integer.parseInt(values[1]));
    				});
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}  	  
    }
    
    public void run() {
    	PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(new HuffmanComparator());
    	
    	for (int i = 0; i < elements.size(); i++) { // O(n)
    		HuffmanNode node = new HuffmanNode(); // New node for the priority queue.
    		node.left = null;
    		node.right = null;
    		node.element = elements.get(i);
    		node.frequency = frequencies.get(i);
    		queue.add(node); // O(log n)
    	}
    	
    	while (queue.size() > 1) { // O(n)
    		HuffmanNode first = queue.poll(); // O(log n)
    		HuffmanNode second = queue.poll(); // O(log n)
    		node = new HuffmanNode();
    		node.left = first;
    		node.right = second;
    		node.element = null;
    		node.frequency = first.frequency + second.frequency;
    		queue.add(node); // The new node goes to the priority queue (take 2, insert 1).
    	} // When we finish the queue will only have one element.
    }
    
    public Map<String, String> getCodes() {
    	return getCodes(node, "");
    }

	private Map<String, String> getCodes(HuffmanNode node, String huffmanCode) {
		if (node.element != null) { // Base case since it is a left node.
			codes.put(node.element, huffmanCode);
			System.out.println(node.element + ":" + huffmanCode);
		}else {
			getCodes(node.left, huffmanCode + "0");
			getCodes(node.right, huffmanCode + "1");
		}
		
		return codes;
	}
}

class HuffmanNode { //each of the nodes of the priority queue
    HuffmanNode left; 
    HuffmanNode right; 
	int frequency; 
    String element;
} 

class HuffmanComparator implements Comparator<HuffmanNode> { //to sort the elements in ascending order or priority
    public int compare(HuffmanNode x, HuffmanNode y) { 
        return x.frequency - y.frequency; 
    } 
} 