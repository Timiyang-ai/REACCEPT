public void moveToBack(E e) {
    if (e != last) {
      unlink(e);
      linkLast(e);
    }
  }