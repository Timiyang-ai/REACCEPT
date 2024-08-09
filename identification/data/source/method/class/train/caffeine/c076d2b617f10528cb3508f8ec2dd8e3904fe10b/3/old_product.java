public void deschedule(Node<K, V> node) {
    unlink(node);
    node.setNextInAccessOrder(null);
    node.setPreviousInAccessOrder(null);
  }