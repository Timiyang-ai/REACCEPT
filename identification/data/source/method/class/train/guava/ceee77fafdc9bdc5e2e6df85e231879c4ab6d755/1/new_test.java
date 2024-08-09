  @Test
  public void inducedSubgraph_graph() {
    Set<Integer> nodeSubset = ImmutableSet.of(N1, N2, N4);

    MutableGraph<Integer> directedGraph = GraphBuilder.directed().allowsSelfLoops(true).build();
    directedGraph.putEdge(N1, N2);
    directedGraph.putEdge(N2, N1);
    directedGraph.putEdge(N1, N3); // only incident to one node in nodeSubset
    directedGraph.putEdge(N4, N4);
    directedGraph.putEdge(5, 6); // not incident to any node in nodeSubset

    MutableGraph<Integer> expectedSubgraph = GraphBuilder.directed().allowsSelfLoops(true).build();
    expectedSubgraph.putEdge(N1, N2);
    expectedSubgraph.putEdge(N2, N1);
    expectedSubgraph.putEdge(N4, N4);

    assertThat(inducedSubgraph(directedGraph, nodeSubset)).isEqualTo(expectedSubgraph);
  }