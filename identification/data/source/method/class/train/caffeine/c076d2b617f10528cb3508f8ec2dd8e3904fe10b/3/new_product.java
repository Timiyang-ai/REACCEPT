public void deschedule(Node<K, V> node) {
    unlink(node);
    node.setNextInVariableOrder(null);
    node.setPreviousInVariableOrder(null);
  }