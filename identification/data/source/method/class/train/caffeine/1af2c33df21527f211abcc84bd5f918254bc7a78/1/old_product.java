public void moveToFront(E e) {
    if (e != first) {
      unlink(e);
      linkFirst(e);
    }
  }