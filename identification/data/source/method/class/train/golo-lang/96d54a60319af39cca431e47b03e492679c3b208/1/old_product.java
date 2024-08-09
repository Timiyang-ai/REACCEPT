@Override
  public int size() {
    if (this.isEmpty()) return 0;
    return 1 + this.tail().size();
  }