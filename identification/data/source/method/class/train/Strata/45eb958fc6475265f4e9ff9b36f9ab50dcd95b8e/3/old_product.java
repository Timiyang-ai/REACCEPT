public double kappa(DoubleArray discountedCashFlow, DoubleArray alpha) {
    final Function1D<Double, Double> swapValue = new Function1D<Double, Double>() {
      @Override
      public Double evaluate(Double x) {
        double error = 0.0;
        for (int loopcf = 0; loopcf < alpha.size(); loopcf++) {
          error += discountedCashFlow.get(loopcf) *
              Math.exp(-0.5 * alpha.get(loopcf) * alpha.get(loopcf) - (alpha.get(loopcf) - alpha.get(0)) * x);
        }
        return error;
      }
    };
    BracketRoot bracketer = new BracketRoot();
    double accuracy = 1.0E-8;
    RidderSingleRootFinder rootFinder = new RidderSingleRootFinder(accuracy);
    double[] range = bracketer.getBracketedPoints(swapValue, -2.0, 2.0);
    return rootFinder.getRoot(swapValue, range[0], range[1]);
  }