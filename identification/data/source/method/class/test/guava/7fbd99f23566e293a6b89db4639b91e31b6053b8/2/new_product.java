public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> graph) {
    MutableValueGraph<N, V> copy = ValueGraphBuilder.from(graph)
        .expectedNodeCount(graph.nodes().size())
        .build();
    for (N node : graph.nodes()) {
      copy.addNode(node);
    }
    for (EndpointPair<N> edge : graph.edges()) {
      copy.putEdgeValue(edge.nodeU(), edge.nodeV(), graph.edgeValue(edge.nodeU(), edge.nodeV()));
    }
    return copy;
  }