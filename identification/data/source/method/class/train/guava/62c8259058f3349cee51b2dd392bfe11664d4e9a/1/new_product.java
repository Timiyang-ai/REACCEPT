@CanIgnoreReturnValue
  boolean addEdge(String e, Integer n1, Integer n2) {
    network.addNode(n1);
    network.addNode(n2);
    return network.addEdge(n1, n2, e);
  }