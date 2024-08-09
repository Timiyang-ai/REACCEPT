@Test
  public void addEdge_nodesNotInGraph() {
    network.addNode(N1);
    assertTrue(network.addEdgeV2(N1, N5, E15));
    assertTrue(network.addEdgeV2(N4, N1, E41));
    assertTrue(network.addEdgeV2(N2, N3, E23));
    assertThat(network.nodes()).containsExactly(N1, N5, N4, N2, N3).inOrder();
    assertThat(network.edges()).containsExactly(E15, E41, E23).inOrder();
    assertThat(network.edgesConnecting(N1, N5)).containsExactly(E15);
    assertThat(network.edgesConnecting(N4, N1)).containsExactly(E41);
    assertThat(network.edgesConnecting(N2, N3)).containsExactly(E23);
    assertThat(network.edgesConnecting(N3, N2)).containsExactly(E23);
  }