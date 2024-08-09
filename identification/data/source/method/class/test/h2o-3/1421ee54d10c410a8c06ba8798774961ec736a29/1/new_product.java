public static boolean stopEarly(ScoreKeeper[] sk, int k, boolean classification, StoppingMetric criterion, double rel_improvement, String what) {
    if (k == 0) return false;
    int len = sk.length - 1; //how many "full"/"conservative" scoring events we have (skip the first)
    if (len < 2*k) return false; //need at least k for SMA and another k to tell whether the model got better or not

    if (criterion==StoppingMetric.AUTO) {
      criterion = classification ? StoppingMetric.logloss : StoppingMetric.deviance;
    }

    boolean moreIsBetter = moreIsBetter(criterion);
    double movingAvg[] = new double[k+1]; //need one moving average value for the last k+1 scoring events
    double lastBeforeK = moreIsBetter ? -Double.MAX_VALUE : Double.MAX_VALUE;
    double bestInLastK = moreIsBetter ? -Double.MAX_VALUE : Double.MAX_VALUE;
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

      int startIdx = sk.length-2*k+i;
      for (int j = 0; j < k; ++j) {
        ScoreKeeper skj = sk[startIdx+j];
        double val;
        switch (criterion) {
          case AUC:
            val = skj._AUC;
            break;
          case MSE:
            val = skj._mse;
            break;
          case deviance:
            val = skj._mean_residual_deviance;
            break;
          case logloss:
            val = skj._logloss;
            break;
          case r2:
            val = skj._r2;
            break;
          case misclassification:
            val = skj._classError;
            break;
          case lift_top_group:
            val = skj._lift;
            break;
          default:
            throw H2O.unimpl("Undefined stopping criterion.");
        }
        movingAvg[i] += val;
      }
      movingAvg[i]/=k;
      if (Double.isNaN(movingAvg[i])) return false;
      if (i==0)
        lastBeforeK = movingAvg[i];
      else
        bestInLastK = moreIsBetter ? Math.max(movingAvg[i], bestInLastK) : Math.min(movingAvg[i], bestInLastK);
    }
    // zero-crossing could be for residual deviance or r^2 -> mark it not yet converged, avoid division by 0 or weird relative improvements math below
    if (Math.signum(ArrayUtils.maxValue(movingAvg)) != Math.signum(ArrayUtils.minValue(movingAvg))) return false;
    if (Math.signum(bestInLastK) != Math.signum(lastBeforeK)) return false;
    assert(lastBeforeK != Double.MAX_VALUE);
    assert(bestInLastK != Double.MAX_VALUE);
    Log.info("Windowed averages (window size " + k + ") of " + what + " " + (k+1) + " " + criterion.toString() + " metrics: " + Arrays.toString(movingAvg));

    double ratio = bestInLastK / lastBeforeK;
    if (Double.isNaN(ratio)) return false;
    boolean improved = moreIsBetter ? ratio > 1+rel_improvement : ratio < 1-rel_improvement;
    Log.info("Checking convergence with " + criterion.toString() + " metric: " + lastBeforeK + " --> " + bestInLastK + (improved ? " (still improving)." : " (converged)."));
    return !improved;
  }