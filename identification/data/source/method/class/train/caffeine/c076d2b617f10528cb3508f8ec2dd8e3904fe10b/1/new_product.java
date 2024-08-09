public void reschedule(Node<K, V> node) {
    if (node.getNextInVariableOrder() != null) {
      unlink(node);
      schedule(node);
    }
  }