public LockResource lockEdge(Edge edge, LockMode mode) {
    return mEdgeLocks.get(edge, mode);
  }