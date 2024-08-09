private static void searchPerformance(final IdPreMap m) {
    final int n = BASEID + ITERATIONS;
    for(int id = BASEID + 1; id <= n; ++id) m.insert(RANDOM.nextInt(id), id, 1);

    for(int i = 0; i < n; ++i) m.pre(i);
  }