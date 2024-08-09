private static Empty empty(RelNode node) {
    return new Empty(node.getCluster(), node.getRowType());
  }