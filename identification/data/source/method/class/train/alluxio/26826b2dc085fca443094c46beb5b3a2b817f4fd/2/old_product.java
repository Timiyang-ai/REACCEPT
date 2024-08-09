public LockResource lockEdge(Edge edge, LockMode mode) {
    return lock(mEdgeLocks.getUnchecked(edge), mode);
  }