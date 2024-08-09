public BytesRef get(BytesRef spare, int index) {
    if (lastElement > index) {
      int offset = offsets[index];
      int length = index == lastElement - 1 ? currentOffset - offset
          : offsets[index + 1] - offset;
      assert spare.offset == 0;
      spare.grow(length);
      spare.length = length;
      pool.readBytes(offset, spare.bytes, spare.offset, spare.length);
      return spare;
    }
    throw new IndexOutOfBoundsException("index " + index
        + " must be less than the size: " + lastElement);
    
  }