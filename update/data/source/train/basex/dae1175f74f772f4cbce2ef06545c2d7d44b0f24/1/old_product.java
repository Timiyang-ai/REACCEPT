public void delete(final int i) {
    Array.move(list, i + 1, -1, --size - i);
  }