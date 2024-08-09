public List<T> topologicalSort() throws CycleException {
		
		Set<T> queue = getNodesWithNoIncomingEdges();
		List<T> result = new ArrayList<>();
		
		// The initial edges are stored.
		List<Edge> initialEdges = new ArrayList<>();
		initialEdges.addAll(edges);
		while (!queue.isEmpty()) {
			T node = queue.iterator().next();
			queue.remove(node);
			result.add(node);
			Set<Edge> edgesStarting = getEdgesStartingWith(node);
			for (Edge edge : edgesStarting) {
				edges.remove(edge);
				if (!hasIncomingEdges(edge.getToNode())) {
					queue.add(edge.getToNode());
				}
			}
		}
		if (!edges.isEmpty()) {
			throw new CycleException(edges.toString(), result);
		}
		// The old edges are restored in order to maintain the graph integrity.
		edges.addAll(initialEdges);
		return result;
	}