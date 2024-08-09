  @Test
  public void transitiveClosure_directedGraph() {
    MutableGraph<Integer> directedGraph = GraphBuilder.directed().allowsSelfLoops(false).build();
    directedGraph.putEdge(N1, N2);
    directedGraph.putEdge(N1, N3);
    directedGraph.putEdge(N2, N3);
    directedGraph.addNode(N4);

    MutableGraph<Integer> expectedClosure = GraphBuilder.directed().allowsSelfLoops(true).build();
    expectedClosure.putEdge(N1, N1);
    expectedClosure.putEdge(N1, N2);
    expectedClosure.putEdge(N1, N3);
    expectedClosure.putEdge(N2, N2);
    expectedClosure.putEdge(N2, N3);
    expectedClosure.putEdge(N3, N3);
    expectedClosure.putEdge(N4, N4);

    checkTransitiveClosure(directedGraph, expectedClosure);
  }