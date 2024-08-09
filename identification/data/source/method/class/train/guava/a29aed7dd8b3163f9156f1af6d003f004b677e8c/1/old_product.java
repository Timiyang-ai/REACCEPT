private static int degree(Graph<?, ?> graph, Object node) {
    if (graph.isDirected()) {
      return IntMath.saturatedAdd(graph.predecessors(node).size(), graph.successors(node).size());
    } else {
      int selfLoops = (graph.allowsSelfLoops() && graph.adjacentNodes(node).contains(node)) ? 1 : 0;
      return IntMath.saturatedAdd(graph.adjacentNodes(node).size(), selfLoops);
    }
  }