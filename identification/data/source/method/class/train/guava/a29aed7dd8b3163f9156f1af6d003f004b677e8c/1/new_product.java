@Override
  public int degree(Object node) {
    if (isDirected()) {
      return IntMath.saturatedAdd(predecessors(node).size(), successors(node).size());
    } else {
      Set<N> neighbors = adjacentNodes(node);
      int selfLoop = (allowsSelfLoops() && neighbors.contains(node)) ? 1 : 0;
      return IntMath.saturatedAdd(neighbors.size(), selfLoop);
    }
  }