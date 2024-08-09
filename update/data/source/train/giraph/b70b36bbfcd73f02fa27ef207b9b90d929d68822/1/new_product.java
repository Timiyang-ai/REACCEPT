public Vertex<I, V, E, M> instantiateVertex(
      I vertexId, V vertexValue, Map<I, E> edges, Iterable<M> messages) {
    MutableVertex<I, V, E, M> mutableVertex =
        (MutableVertex<I, V, E, M>) getConf().createVertex();
    mutableVertex.setGraphState(getGraphState());
    mutableVertex.initialize(vertexId, vertexValue, edges, messages);
    return mutableVertex;
  }