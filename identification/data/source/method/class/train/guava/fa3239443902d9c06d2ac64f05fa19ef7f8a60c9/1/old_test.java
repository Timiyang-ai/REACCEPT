  @Test
  @SuppressWarnings("CheckReturnValue")
  public void forTree_acceptsDirectedGraph() throws Exception {
    MutableGraph<String> graph = GraphBuilder.directed().build();
    graph.putEdge("a", "b");

    Traverser.forTree(graph); // Does not throw
  }