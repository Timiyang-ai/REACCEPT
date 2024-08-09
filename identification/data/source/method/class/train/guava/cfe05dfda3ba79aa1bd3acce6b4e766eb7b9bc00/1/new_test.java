  @Test
  public void transpose_undirectedGraph() {
    MutableGraph<Integer> undirectedGraph = GraphBuilder.undirected().build();
    undirectedGraph.putEdge(N1, N2);

    assertThat(transpose(undirectedGraph)).isSameInstanceAs(undirectedGraph);
  }