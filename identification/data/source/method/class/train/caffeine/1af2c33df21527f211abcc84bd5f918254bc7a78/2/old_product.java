@Override
  public E getLast() {
    checkNotEmpty();
    return peekLast();
  }