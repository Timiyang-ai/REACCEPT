@Test(expected = CycleException.class)
	public void topologicalSort_shouldThrowCycleException() throws CycleException {
		
		Graph<String> graph = new Graph<String>();
		
		graph.addNode("E");
		graph.addNode("D");
		graph.addNode("C");
		graph.addNode("B");
		graph.addNode("A");
		
		graph.addEdge(graph.new Edge(
		                             "A", "B"));
		graph.addEdge(graph.new Edge(
		                             "A", "C"));
		graph.addEdge(graph.new Edge(
		                             "B", "C"));
		graph.addEdge(graph.new Edge(
		                             "B", "D"));
		graph.addEdge(graph.new Edge(
		                             "D", "A"));
		
		graph.topologicalSort();
	}