public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> nodes) {
    MutableGraph<N> subgraph = GraphBuilder.from(graph).build();
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