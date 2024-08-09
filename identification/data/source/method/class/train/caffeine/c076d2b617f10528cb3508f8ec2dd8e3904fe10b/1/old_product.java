public void reschedule(Node<K, V> node) {
    if (node.getNextInAccessOrder() != null) {
      unlink(node);
      schedule(node);
    }
  }