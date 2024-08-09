public void schedule(Node<K, V> node) {
    Node<K, V> sentinel = findBucket(node.getAccessTime());
    link(sentinel, node);
  }