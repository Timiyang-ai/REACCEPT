public static <N> BasicGraph<N> transitiveClosure(ValueGraph<N, ?> graph) {
    MutableBasicGraph<N> transitiveClosure =
        BasicGraphBuilder.from(graph).allowsSelfLoops(true).build();
    // Every node is, at a minimum, reachable from itself. Since the resulting transitive closure
    // will have no isolated nodes, we can skip adding nodes explicitly and let putEdge() do it.

    if (graph.isDirected()) {
      // Note: works for both directed and undirected graphs, but we only use in the directed case.
      for (N node : graph.nodes()) {
        for (N reachableNode : reachableNodes(graph, node)) {
          transitiveClosure.putEdge(node, reachableNode);
        }
      }
    } else {
      // An optimization for the undirected case: for every node B reachable from node A,
      // node A and node B have the same reachability set.
      Set<N> visitedNodes = new HashSet<N>();
      for (N node : graph.nodes()) {
        if (!visitedNodes.contains(node)) {
          ImmutableList<N> reachableNodes = ImmutableList.copyOf(reachableNodes(graph, node));
          visitedNodes.addAll(reachableNodes);
          for (int a = 0; a < reachableNodes.size(); ++a) {
            N nodeA = reachableNodes.get(a);
            for (int b = a; b < reachableNodes.size(); ++b) {
              N nodeB = reachableNodes.get(b);
              transitiveClosure.putEdge(nodeA, nodeB);
            }
          }
        }
      }
    }

    return transitiveClosure;
  }