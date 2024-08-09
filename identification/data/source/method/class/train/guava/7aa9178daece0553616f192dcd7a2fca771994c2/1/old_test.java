@Test
  public void addEdge_nodesNotInGraph() {
    graph.addNode(N1);
    assertTrue(graph.addEdge(N1, N5));
    assertTrue(graph.addEdge(N4, N1));
    assertTrue(graph.addEdge(N2, N3));
    assertThat(graph.nodes()).containsExactly(N1, N5, N4, N2, N3).inOrder();
    assertThat(graph.adjacentNodes(N1)).containsExactly(N4, N5);
    assertThat(graph.adjacentNodes(N2)).containsExactly(N3);
    assertThat(graph.adjacentNodes(N3)).containsExactly(N2);
    assertThat(graph.adjacentNodes(N4)).containsExactly(N1);
    assertThat(graph.adjacentNodes(N5)).containsExactly(N1);
  }