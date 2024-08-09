public static double logSum(double logA, double logB) {
    if (Double.isInfinite(logA)) {
      return logB;
    }
    if (Double.isInfinite(logB)) {
      return logA;
    }
    if (logA > logB) {
      double dif = logA - logB;
      return dif >= 30d ? logA : logA + Math.log(1 + Math.exp(-dif));
    } else {
      double dif = logB - logA;
      return dif >= 30d ? logB : logB + Math.log(1 + Math.exp(-dif));
    }
  }