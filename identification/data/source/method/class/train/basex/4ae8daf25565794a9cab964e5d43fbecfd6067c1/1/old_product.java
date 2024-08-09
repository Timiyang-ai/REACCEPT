public long time() {
    final long time2 = System.nanoTime();
    final long diff = time2 - time;
    time = time2;
    return diff;
  }