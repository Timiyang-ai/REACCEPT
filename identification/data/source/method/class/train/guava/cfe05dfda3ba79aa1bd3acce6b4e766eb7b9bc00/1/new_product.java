public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> graph) {
    if (!graph.isDirected()) {
      return graph; // the transpose of an undirected graph is an identical graph
    }

    if (graph instanceof TransposedValueGraph) {
      return ((TransposedValueGraph<N, V>) graph).graph;
    }

    return new TransposedValueGraph<N, V>(graph);
  }