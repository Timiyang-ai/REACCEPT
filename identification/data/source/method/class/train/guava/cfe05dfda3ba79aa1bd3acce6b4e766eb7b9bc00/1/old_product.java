public static <N> BasicGraph<N> transpose(BasicGraph<N> graph) {
    return asBasicGraph(transpose((Graph<N, Presence>) graph));
  }