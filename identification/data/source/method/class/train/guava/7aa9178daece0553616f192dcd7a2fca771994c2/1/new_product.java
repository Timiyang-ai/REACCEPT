@CanIgnoreReturnValue
  boolean addEdge(Integer n1, Integer n2) {
    graph.addNode(n1);
    graph.addNode(n2);
    return graph.putEdge(n1, n2);
  }