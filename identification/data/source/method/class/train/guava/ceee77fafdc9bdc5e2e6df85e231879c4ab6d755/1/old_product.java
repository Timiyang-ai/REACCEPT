public static <N> MutableBasicGraph<N> inducedSubgraph(BasicGraph<N> graph,
      Iterable<? extends N> nodes) {
    MutableBasicGraph<N> subgraph = BasicGraphBuilder.from(graph).build();
    for (N node : nodes) {
      subgraph.addNode(node);
    }
    for (N node : subgraph.nodes()) {
      for (N successorNode : graph.successors(node)) {
        if (subgraph.nodes().contains(successorNode)) {
          subgraph.putEdge(node, successorNode);
        }
      }
    }
    return subgraph;
  }