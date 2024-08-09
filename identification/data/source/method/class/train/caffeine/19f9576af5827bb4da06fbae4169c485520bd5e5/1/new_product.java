boolean offer(Node<E> node) {
    for (;;) {
      Node<E> h = head;
      if (casHead(h, node)) {
        h.lazySetNext(node);
        return true;
      }

      // TODO(ben): Add combining backoff
    }
  }