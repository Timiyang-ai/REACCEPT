@Override
  public void addFirst(E e) {
    if (!offerFirst(e)) {
      throw new IllegalArgumentException();
    }
  }