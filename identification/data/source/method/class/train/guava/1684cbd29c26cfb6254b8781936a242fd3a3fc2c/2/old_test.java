  @Test
  public void edgeValue_missing() {
    graph = ValueGraphBuilder.directed().build();

    assertThat(graph.edgeValueOrDefault(1, 2, DEFAULT)).isEqualTo(DEFAULT);
    assertThat(graph.edgeValueOrDefault(2, 1, DEFAULT)).isEqualTo(DEFAULT);
    assertThat(graph.edgeValueOrDefault(1, 2, null)).isNull();
    assertThat(graph.edgeValueOrDefault(2, 1, null)).isNull();

    graph.putEdgeValue(1, 2, "valueA");
    graph.putEdgeValue(2, 1, "valueB");
    assertThat(graph.edgeValueOrDefault(1, 2, DEFAULT)).isEqualTo("valueA");
    assertThat(graph.edgeValueOrDefault(2, 1, DEFAULT)).isEqualTo("valueB");
    assertThat(graph.edgeValueOrDefault(1, 2, null)).isEqualTo("valueA");
    assertThat(graph.edgeValueOrDefault(2, 1, null)).isEqualTo("valueB");

    graph.removeEdge(1, 2);
    graph.putEdgeValue(2, 1, "valueC");
    assertThat(graph.edgeValueOrDefault(1, 2, DEFAULT)).isEqualTo(DEFAULT);
    assertThat(graph.edgeValueOrDefault(2, 1, DEFAULT)).isEqualTo("valueC");
    assertThat(graph.edgeValueOrDefault(1, 2, null)).isNull();
    assertThat(graph.edgeValueOrDefault(2, 1, null)).isEqualTo("valueC");
  }