public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
    checkNotNull(graph, "graph");
    // TODO(b/28087289): we can remove this restriction when Graph supports parallel edges
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