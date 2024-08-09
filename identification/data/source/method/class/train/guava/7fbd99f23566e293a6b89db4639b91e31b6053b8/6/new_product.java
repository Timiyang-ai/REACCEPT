public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
    return copyOfInternal(
        graph,
        GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()),
        Predicates.alwaysTrue());
  }