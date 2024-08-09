public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
    return copyOf(graph, Predicates.alwaysTrue());
  }