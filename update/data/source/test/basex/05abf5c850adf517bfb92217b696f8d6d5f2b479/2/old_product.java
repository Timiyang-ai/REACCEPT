private static void deletePerformance(final IdPreMap m, final DummyIdPreMap b) {
    // prepare <pre, id> pairs:
    final int[][] d = new int[BASEID + 1][2];
    for(int i = 0, id = BASEID + 1; i < d.length; --id, ++i) {
      d[i][0] = RANDOM.nextInt(id);
      d[i][1] = b.id(d[i][0]);
      b.delete(d[i][0], d[i][1], -1);
    }
    // perform the test:
    final Performance p = new Performance();
    for(final int[] dd : d) m.delete(dd[0], dd[1], -1);
    if(VERBOSE) Util.errln(d.length + " records deleted in: " + p);
  }