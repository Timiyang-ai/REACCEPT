public double lambda(DoubleArray discountedCashFlow, DoubleArray alpha2, DoubleArray hwH) {
    final Function1D<Double, Double> swapValue = new Function1D<Double, Double>() {
      @Override
      public Double evaluate(final Double x) {
        double value = 0.0;
        for (int loopcf = 0; loopcf < alpha2.size(); loopcf++) {
          value += discountedCashFlow.get(loopcf) * Math.exp(-0.5 * alpha2.get(loopcf) - hwH.get(loopcf) * x);
        }
        return value;
      }
    };
    BracketRoot bracketer = new BracketRoot();
    double accuracy = 1.0E-8;
    RidderSingleRootFinder rootFinder = new RidderSingleRootFinder(accuracy);
    double[] range = bracketer.getBracketedPoints(swapValue, -2.0, 2.0);
    return rootFinder.getRoot(swapValue, range[0], range[1]);
  }