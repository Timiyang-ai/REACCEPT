public static void percentiles(long[] counts, double[] pcts, double[] results) {
    Preconditions.checkArg(counts.length == BUCKET_VALUES.length,
        "counts is not the same size as buckets array");
    Preconditions.checkArg(pcts.length > 0, "pct array cannot be empty");
    Preconditions.checkArg(pcts.length == results.length,
        "pcts is not the same size as results array");

    long total = 0L;
    for (long c : counts) {
      total += c;
    }

    int pctIdx = 0;

    long prev = 0;
    double prevP = 0.0;
    long prevB = 0;
    for (int i = 0; i < BUCKET_VALUES.length; ++i) {
      long next = prev + counts[i];
      double nextP = 100.0 * next / total;
      long nextB = BUCKET_VALUES[i];
      while (pctIdx < pcts.length && nextP >= pcts[pctIdx]) {
        double f = (pcts[pctIdx] - prevP) / (nextP - prevP);
        results[pctIdx] = f * (nextB - prevB) + prevB;
        ++pctIdx;
      }
      if (pctIdx >= pcts.length) break;
      prev = next;
      prevP = nextP;
      prevB = nextB;
    }

    double nextP = 100.0;
    long nextB = Long.MAX_VALUE;
    while (pctIdx < pcts.length) {
      double f = (pcts[pctIdx] - prevP) / (nextP - prevP);
      results[pctIdx] = f * (nextB - prevB) + prevB;
      ++pctIdx;
    }
  }