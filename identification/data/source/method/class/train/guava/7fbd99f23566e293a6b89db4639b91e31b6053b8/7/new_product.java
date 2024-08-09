public static <N> MutableBasicGraph<N> copyOf(BasicGraph<N> graph) {
    MutableBasicGraph<N> copy = BasicGraphBuilder.from(graph)
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