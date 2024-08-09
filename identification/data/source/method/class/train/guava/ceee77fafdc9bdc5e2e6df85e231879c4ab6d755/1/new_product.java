public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> graph,
      Iterable<? extends N> nodes) {
    MutableValueGraph<N, V> subgraph = ValueGraphBuilder.from(graph).build();
    for (N node : nodes) {
      subgraph.addNode(node);
    }
    for (N node : subgraph.nodes()) {
      for (N successorNode : graph.successors(node)) {
        if (subgraph.nodes().contains(successorNode)) {
          subgraph.putEdgeValue(node, successorNode, graph.edgeValue(node, successorNode));
        }
      }
    }
    return subgraph;
  }