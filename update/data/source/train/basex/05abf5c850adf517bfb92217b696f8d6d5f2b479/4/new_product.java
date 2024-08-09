private static void insertPerformance(final IdPreMap m) {
    // prepare <pre, id> pairs:
    final int[][] d = new int[ITERATIONS][2];
    for(int i = 0, id = BASEID + 1; i < d.length; ++id, ++i) {
      d[i][0] = RANDOM.nextInt(id);
      d[i][1] = id;
    }
    // perform the actual test:
    for(final int[] a : d) m.insert(a[0], a[1], 1);
  }