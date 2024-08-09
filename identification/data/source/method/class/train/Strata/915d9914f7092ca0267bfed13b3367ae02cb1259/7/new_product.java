private double discountFactor(double relativeTime) {
    // short cut rate lookup
    if (relativeTime == 0) {
      return 1.0;
    }
    return Math.exp(-relativeTime * curve.yValue(relativeTime));
  }