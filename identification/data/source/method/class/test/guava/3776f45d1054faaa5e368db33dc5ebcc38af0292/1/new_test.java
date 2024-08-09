@Test
  public void addEdge_nodesNotInGraph() {
    network.addNode(N1);
    assertTrue(network.addEdge(E15, N1, N5));
    assertTrue(network.addEdge(E41, N4, N1));
    assertTrue(network.addEdge(E23, N2, N3));
    assertThat(network.nodes()).containsExactly(N1, N5, N4, N2, N3).inOrder();
    assertThat(network.edges()).containsExactly(E15, E41, E23).inOrder();
    assertThat(network.edgesConnecting(N1, N5)).containsExactly(E15);
    assertThat(network.edgesConnecting(N4, N1)).containsExactly(E41);
    assertThat(network.edgesConnecting(N2, N3)).containsExactly(E23);
    // Direction of the added edge is correctly handled
    assertThat(network.edgesConnecting(N3, N2)).isEmpty();
  }