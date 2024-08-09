	@Test
	public void topologicalSort_shouldSortGraphInTopologicalOrder() throws CycleException {
		
		Graph<String> graph = new Graph<>();
		
		graph.addNode("E");
		graph.addNode("D");
		graph.addNode("C");
		graph.addNode("B");
		graph.addNode("A");
		
		graph.addEdge(graph.new Edge(
		                             "A", "B"));
		graph.addEdge(graph.new Edge(
		                             "B", "C"));
		graph.addEdge(graph.new Edge(
		                             "A", "C"));
		graph.addEdge(graph.new Edge(
		                             "B", "D"));
		graph.addEdge(graph.new Edge(
		                             "D", "E"));
		
		List<String> sortedNodes = graph.topologicalSort();
		Assert.assertTrue(sortedNodes.indexOf("A") < sortedNodes.indexOf("B"));
		Assert.assertTrue(sortedNodes.indexOf("A") < sortedNodes.indexOf("C"));
		Assert.assertTrue(sortedNodes.indexOf("B") < sortedNodes.indexOf("C"));
		Assert.assertTrue(sortedNodes.indexOf("B") < sortedNodes.indexOf("D"));
		Assert.assertTrue(sortedNodes.indexOf("D") < sortedNodes.indexOf("E"));
	}