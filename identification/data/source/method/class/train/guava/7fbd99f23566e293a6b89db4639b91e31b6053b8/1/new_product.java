@SuppressWarnings("unchecked")
  public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
    checkNotNull(graph, "graph");
    // TODO(b/28087289): we can remove this restriction when Graph supports parallel edges
    checkArgument(!((graph instanceof Network) && ((Network<N, ?>) graph).allowsParallelEdges()),
        NETWORK_WITH_PARALLEL_EDGE);
    MutableGraph<N> copy = GraphBuilder.from(graph)
        .expectedNodeCount(graph.nodes().size())
        .build();

    for (N node : graph.nodes()) {
      copy.addNode(node);
      for (N successor : graph.successors(node)) {
        // TODO(b/28087289): Ensure that multiplicity is preserved if parallel edges are supported.
        copy.addEdge(node, successor);
      }
    }

    return copy;
  }