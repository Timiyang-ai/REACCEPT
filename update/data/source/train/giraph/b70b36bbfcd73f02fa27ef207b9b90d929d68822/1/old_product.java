public Vertex<I, V, E, M> instantiateVertex(
      I vertexId, V vertexValue, Map<I, E> edges, Iterable<M> messages) {
    MutableVertex<I, V, E, M> mutableVertex =
        (MutableVertex<I, V, E, M>) BspUtils
        .<I, V, E, M>createVertex(getContext().getConfiguration());
    mutableVertex.setGraphState(getGraphState());
    mutableVertex.initialize(vertexId, vertexValue, edges, messages);
    return mutableVertex;
  }