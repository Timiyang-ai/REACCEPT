public void increment(@Nonnull E e) {
    int item = e.hashCode();
    int start = (item & 3) << 2;

    // Loop unrolling improves throughput by 5m ops/s
    int index0 = indexOf(item, 0);
    int index1 = indexOf(item, 1);
    int index2 = indexOf(item, 2);
    int index3 = indexOf(item, 3);

    boolean added = incrementAt(index0, start);
    added |= incrementAt(index1, start + 1);
    added |= incrementAt(index2, start + 2);
    added |= incrementAt(index3, start + 3);

    if (added && (++size == sampleSize)) {
      reset();
    }
  }