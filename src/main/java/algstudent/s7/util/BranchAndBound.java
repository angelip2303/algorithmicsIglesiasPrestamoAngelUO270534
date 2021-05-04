package algstudent.s7.util;

import java.util.ArrayList;

/**
 * Main class to solve problems using the Branch and Bound technique
 * We need to extend it for any specific problem
 */
public abstract class BranchAndBound {
	protected Heap ds; //Nodes to be explored (not used nodes)
	protected Node bestNode; //To save the final node of the best solution
	protected Node rootNode; //Initial node
	protected int pruneLimit; //To prune nodes above this value
	
	private int numberOfProcessedNodes;
	private int numberOfGeneratedNodes;
	private int numberOfTrimmedNodes;
	
	/**
	 * Constructor for BrancAndBount objects
	 */
	public BranchAndBound() {
		ds = new Heap(); //We create an instance of the Heap class to save the nodes
		
		numberOfProcessedNodes = 0;
		numberOfGeneratedNodes = 0;
		numberOfTrimmedNodes = 0;
	}
	      
	/**
	 * Manages all the process, from the beginning to the end
	 * @param rootNode Starting state of the problem
	 */
	public void branchAndBound(Node rootNode) { 
		ds.insert(rootNode); //First node to be explored
		numberOfGeneratedNodes++;
		
		pruneLimit = rootNode.initialValuePruneLimit();

		while (!ds.empty() && ds.estimateBest() < pruneLimit) {
			Node node = ds.extractBestNode();
			
			ArrayList<Node> children = node.expand(); 
			numberOfGeneratedNodes += children.size();
			
			for (Node child : children)
				// We will "lose" some nodes that are solution
				if (child.isSolution()) {
					int cost = child.getHeuristicValue();
					if (cost < pruneLimit) {
						pruneLimit = cost;
						bestNode = child;
					} 
				}
				else
					// The node is processed
					if (child.getHeuristicValue() < pruneLimit) {
						ds.insert(child);
						numberOfProcessedNodes++;
					// The node is trimmed
					}else
						numberOfTrimmedNodes++;
		} //while
	}
		
	public int getNumberOfProcessedNodes() {
		return numberOfProcessedNodes;
	}

	public int getNumberOfGeneratedNodes() {
		return numberOfGeneratedNodes;
	}

	public int getNumberOfTrimmedNodes() {
		return numberOfTrimmedNodes;
	}

	/**
	 * Gets the root node
	 * @return The root node
	 */
    public Node getRootNode() {
    	return rootNode;
    }
    
	/**
	 * Gets the best node
	 * @return The best node
	 */
    public Node getBestNode() {
    	return bestNode;
    }

    /**
     * Prints the solution from the root node to the best node
     */
    public void printSolutionTrace() {
    	if (bestNode == null) {
			System.out.println("Original:");
			System.out.println(rootNode.toString());
			System.out.println("THERE IS NO SOLUTION");
    	} 
    	else {
    		//Extract the path of the used nodes from bestNode to the rootNode
            ArrayList<Node> result = ds.extractUsedNodesFrom(bestNode);

            for (int i = 0; i < result.size();  i++) {
    			if (i == 0) 
    				System.out.println("Original:");
    			else 
    				System.out.println("Step " + i + ":");
    			System.out.println(result.get(result.size()-i-1).toString());
    	    }
            System.out.println("\nSolution with " + bestNode.getDepth() + " step(s).");	
    	}
    }
}
