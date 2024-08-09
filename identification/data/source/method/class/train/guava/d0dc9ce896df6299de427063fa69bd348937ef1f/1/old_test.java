@Test
  public void equivalent_directedVsUndirected() {
    graph.putEdge(N1, N2);

    MutableGraph<Integer> g2 = createGraph(oppositeType(edgeType));
    g2.putEdge(N1, N2);

    assertThat(Graphs.equivalent(graph, g2)).isFalse();
  }