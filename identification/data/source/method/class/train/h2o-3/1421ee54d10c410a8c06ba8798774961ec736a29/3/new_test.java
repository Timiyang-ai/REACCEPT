  private static boolean stopEarly(double[] val, int k, double tolerance, boolean moreIsBetter, boolean verbose) {
    if (val.length-1 < 2*k) return false; //need 2k scoring events (+1 to skip the very first one, which might be full of NaNs)
    double[] moving_avg = new double[k+1]; //one moving avg for the last k+1 scoring events (1 is reference, k consecutive attempts to improve)

    // compute moving average(s)
    for (int i=0;i<moving_avg.length;++i) {
      moving_avg[i]=0;
      int startidx=val.length-2*k+i;
      for (int j=0;j<k;++j)
        moving_avg[i]+=val[startidx+j];
      moving_avg[i]/=k;
    }
    if (verbose) Log.info("JUnit: moving averages: " + Arrays.toString(moving_avg));

    // check if any of the moving averages is better than the reference (by at least tolerance relative improvement)
    double ref = moving_avg[0];
    boolean improved = false;
    for (int i=1;i<moving_avg.length;++i) {
      if (moreIsBetter)
        improved |= (moving_avg[i] > ref*(1+tolerance));
      else
        improved |= (moving_avg[i] < ref*(1-tolerance));
      if (improved && verbose)
        Log.info("JUnit: improved from " + ref + " to " + moving_avg[i] + " by at least " + tolerance + " relative tolerance");
    }
    if (improved) {
      if (verbose) Log.info("JUnit: Still improving.");
      return false;
    }
    else {
      if (verbose) Log.info("JUnit: Stopped.");
      return true;
    }
  }