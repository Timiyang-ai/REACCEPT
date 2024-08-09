public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
    checkNotNull(graph, "graph");
    // TODO(user): Consider dropping this restriction. Would this do what users expect?
    checkArgument(!allowsParallelEdges(graph), NETWORK_WITH_PARALLEL_EDGE);
    MutableGraph<N> copy = GraphBuilder.from(graph)
        .expectedNodeCount(graph.nodes().size())
        .build();

    for (N node : graph.nodes()) {
      checkState(copy.addNode(node));
    }
    for (Endpoints<N> endpoints : endpointsInternal(graph)) {
      checkState(copy.addEdge(endpoints.nodeA(), endpoints.nodeB()));
    }

    return copy;
  }