@Test
  public void addEdge_nodesNotInGraph() {
    graph.addNode(N1);
    assertTrue(graph.addEdge(E15, N1, N5));
    assertTrue(graph.addEdge(E41, N4, N1));
    assertTrue(graph.addEdge(E23, N2, N3));
    assertThat(graph.nodes()).containsExactly(N1, N5, N4, N2, N3).inOrder();
    assertThat(graph.edges()).containsExactly(E15, E41, E23).inOrder();
    assertThat(graph.edgesConnecting(N1, N5)).containsExactly(E15);
    assertThat(graph.edgesConnecting(N4, N1)).containsExactly(E41);
    assertThat(graph.edgesConnecting(N2, N3)).containsExactly(E23);
    // Direction of the added edge is correctly handled
    assertThat(graph.edgesConnecting(N3, N2)).isEmpty();
  }