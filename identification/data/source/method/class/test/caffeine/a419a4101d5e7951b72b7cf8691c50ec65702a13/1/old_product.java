public void drain(Consumer<E> consumer) {
    final int start = segmentIndex();
    final int end = start + NUMBER_OF_SEGMENTS;
    for (int i = start; i < end; i++) {
      drainSegment(consumer, i & SEGMENT_MASK);
    }
  }