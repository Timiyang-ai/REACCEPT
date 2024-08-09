public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
    return copyOfInternal(graph, Predicates.alwaysTrue());
  }