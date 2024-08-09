public static boolean stopEarly(ScoreKeeper[] sk, int k, ProblemType type, IStoppingMetric criterion, double rel_improvement, String what, boolean verbose) {
    if (k == 0) return false;
    int len = sk.length - 1; //how many "full"/"conservative" scoring events we have (skip the first)
    if (len < 2*k) return false; //need at least k for SMA and another k to tell whether the model got better or not

    if (StoppingMetric.AUTO.equals(criterion)) {
      criterion = type.defaultMetric();
    }

    IConvergenceStrategy convergenceStrategy = criterion.getConvergenceStrategy();
    double movingAvg[] = new double[k+1]; //need one moving average value for the last k+1 scoring events
    double lastBeforeK = Double.MAX_VALUE;
    double minInLastK = Double.MAX_VALUE;
    double maxInLastK = -Double.MAX_VALUE;
    for (int i=0;i<movingAvg.length;++i) {
      movingAvg[i] = 0;
      // compute k+1 simple moving averages of window size k
      // need to go back 2*k steps

      // Example: 20 scoring events, k=3
      // need to go back from idx 19 to idx 14
      // movingAvg[0] is based on scoring events indices 14,15,16 <- reference
      // movingAvg[1] is based on scoring events indices 15,16,17 <- first "new" smooth score
      // movingAvg[2] is based on scoring events indices 16,17,18 <- second "new" smooth score
      // movingAvg[3] is based on scoring events indices 17,18,19 <- third "new" smooth score

      // Example: 18 scoring events, k=2
      // need to go back from idx 17 to idx 14
      // movingAvg[0] is based on scoring events indices 14,15 <- reference
      // movingAvg[1] is based on scoring events indices 15,16 <- first "new" smooth score
      // movingAvg[2] is based on scoring events indices 16,17 <- second "new" smooth score

      // Example: 18 scoring events, k=1
      // need to go back from idx 17 to idx 16
      // movingAvg[0] is based on scoring event index 16 <- reference
      // movingAvg[1] is based on scoring event index 17 <- first "new" score

      int startIdx = sk.length-2*k+i;
      for (int j = 0; j < k; ++j) {
        ScoreKeeper skj = sk[startIdx+j];
        double val = criterion.metricValue(skj);
        movingAvg[i] += val;
      }
      movingAvg[i]/=k;
      if (Double.isNaN(movingAvg[i])) return false;
      if (i==0)
        lastBeforeK = movingAvg[i];
      else {
        minInLastK = Math.min(movingAvg[i], minInLastK);
        maxInLastK = Math.max(movingAvg[i], maxInLastK);
      }
    }
    assert(lastBeforeK != Double.MAX_VALUE);
    assert(maxInLastK != -Double.MAX_VALUE);
    assert(minInLastK != Double.MAX_VALUE);

    if (verbose)
      Log.info("Windowed averages (window size " + k + ") of " + what + " " + (k+1) + " " + criterion.toString() + " metrics: " + Arrays.toString(movingAvg));

    if (criterion.isLowerBoundBy0() && lastBeforeK == 0.0) {
      Log.info("Checking convergence with " + criterion.toString() + " metric: " + lastBeforeK + " (metric converged to its lower bound).");
      return true;
    }

    final double extremePoint = convergenceStrategy.extremePoint(lastBeforeK, minInLastK, maxInLastK);

    // zero-crossing could be for residual deviance or r^2 -> mark it not yet stopEarly, avoid division by 0 or weird relative improvements math below
    if (Math.signum(ArrayUtils.maxValue(movingAvg)) != Math.signum(ArrayUtils.minValue(movingAvg))) return false;
    if (Math.signum(extremePoint) != Math.signum(lastBeforeK)) 
      return false;

    boolean stopEarly = convergenceStrategy.stopEarly(lastBeforeK, minInLastK, maxInLastK, rel_improvement);

    if (verbose)
      Log.info("Checking convergence with " + criterion.toString() + " metric: " + lastBeforeK + " --> " + extremePoint + (stopEarly ? " (converged)." : " (still improving)."));
    return stopEarly;
  }