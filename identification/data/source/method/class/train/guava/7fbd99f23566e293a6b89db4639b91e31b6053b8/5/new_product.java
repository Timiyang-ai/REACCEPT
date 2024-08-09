public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
    MutableGraph<N> copy = GraphBuilder.from(graph)
        .expectedNodeCount(graph.nodes().size())
        .build();
    for (N node : graph.nodes()) {
      copy.addNode(node);
    }
    for (Endpoints<N> endpoints : graph.edges()) {
      copy.putEdge(endpoints.nodeA(), endpoints.nodeB());
    }
    return copy;
  }