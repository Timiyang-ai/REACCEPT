public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
    checkNotNull(graph, "graph");
    MutableGraph<N> copy = GraphBuilder.from(graph)
        .expectedNodeCount(graph.nodes().size())
        .build();

    for (N node : graph.nodes()) {
      checkState(copy.addNode(node));
    }
    for (Endpoints<N> endpoints : endpointsInternal(graph)) {
      checkState(copy.putEdge(endpoints.nodeA(), endpoints.nodeB()));
    }

    return copy;
  }