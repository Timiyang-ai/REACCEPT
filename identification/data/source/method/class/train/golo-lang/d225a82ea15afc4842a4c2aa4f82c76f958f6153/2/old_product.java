public Object get(int index) {
    if (index < 0 || this.isEmpty()) throw new IndexOutOfBoundsException();
    if (index == 0) return this.head();
    return this.tail().get(index - 1);
  }