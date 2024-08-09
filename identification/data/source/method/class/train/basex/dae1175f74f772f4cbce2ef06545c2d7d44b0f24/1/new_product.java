public void deleteAt(final int index) {
    Array.move(list, index + 1, -1, --size - index);
  }