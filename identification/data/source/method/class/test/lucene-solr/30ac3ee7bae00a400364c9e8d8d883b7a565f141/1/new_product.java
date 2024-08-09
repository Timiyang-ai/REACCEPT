public BytesRef get(BytesRefBuilder spare, int index) {
    if (lastElement > index) {
      int offset = offsets[index];
      int length = index == lastElement - 1 ? currentOffset - offset
          : offsets[index + 1] - offset;
      spare.grow(length);
      spare.setLength(length);
      pool.readBytes(offset, spare.bytes(), 0, spare.length());
      return spare.get();
    }
    throw new IndexOutOfBoundsException("index " + index
        + " must be less than the size: " + lastElement);
    
  }