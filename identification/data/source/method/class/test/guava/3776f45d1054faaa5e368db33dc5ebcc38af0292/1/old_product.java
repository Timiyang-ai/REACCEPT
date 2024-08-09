@CanIgnoreReturnValue
  boolean addEdge(String e, Integer n1, Integer n2) {
    graph.addNode(n1);
    graph.addNode(n2);
    return graph.addEdge(e, n1, n2);
  }