  @Test
  public void degree_oneEdge() {
    putEdge(N1, N2);
    assertThat(graph.degree(N1)).isEqualTo(1);
    assertThat(graph.degree(N2)).isEqualTo(1);
  }