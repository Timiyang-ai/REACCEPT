private static EmptyRel empty(RelNode node) {
    return new EmptyRel(node.getCluster(), node.getRowType());
  }