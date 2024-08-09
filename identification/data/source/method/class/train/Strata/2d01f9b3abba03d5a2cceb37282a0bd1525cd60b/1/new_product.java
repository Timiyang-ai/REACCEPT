public static ValueDerivatives priceAdjoint(
      double forward,
      double strike,
      double timeToExpiry,
      double lognormalVol,
      boolean isCall) {

    ArgChecker.isTrue(forward >= 0d, "negative/NaN forward; have {}", forward);
    ArgChecker.isTrue(strike >= 0d, "negative/NaN strike; have {}", strike);
    ArgChecker.isTrue(timeToExpiry >= 0d, "negative/NaN timeToExpiry; have {}", timeToExpiry);
    ArgChecker.isTrue(lognormalVol >= 0d, "negative/NaN lognormalVol; have {}", lognormalVol);

    double sigmaRootT = lognormalVol * Math.sqrt(timeToExpiry);
    if (Double.isNaN(sigmaRootT)) {
      log.info("lognormalVol * Math.sqrt(timeToExpiry) ambiguous");
      sigmaRootT = 1d;
    }
    int sign = isCall ? 1 : -1;
    boolean bFwd = (forward > LARGE);
    boolean bStr = (strike > LARGE);
    boolean bSigRt = (sigmaRootT > LARGE);
    double d1 = 0d;
    double d2 = 0d;

    if (bFwd && bStr) {
      log.info("(large value)/(large value) ambiguous");
      double price = isCall ? (forward >= strike ? forward : 0d) : (strike >= forward ? strike : 0d); // ???
      return ValueDerivatives.of(price, DoubleArray.filled(4)); // ??
    }
    if (sigmaRootT < SMALL) {
      boolean isItm = (sign * (forward - strike)) > 0;
      double price = isItm ? sign * (forward - strike) : 0d;
      return ValueDerivatives.of(price, DoubleArray.of(isItm ? sign : 0d, isItm ? -sign : 0d, 0d, 0d));
    }
    if (Math.abs(forward - strike) < SMALL || bSigRt) {
      d1 = 0.5 * sigmaRootT;
      d2 = -0.5 * sigmaRootT;
    } else {
      d2 = Math.log(forward / strike) / sigmaRootT - 0.5 * sigmaRootT;
      d1 = d2 + sigmaRootT;
    }

    double nF = NORMAL.getCDF(sign * d1);
    double nS = NORMAL.getCDF(sign * d2);
    double first = nF == 0d ? 0d : forward * nF;
    double second = nS == 0d ? 0d : strike * nS;
    double res = sign * (first - second);
    double price = Math.max(0.0d, res);

    // Backward sweep
    double resBar = 1.0;
    double firstBar = sign * resBar;
    double secondBar = -sign * resBar;
    double forwardBar = nF * firstBar;
    double strikeBar = nS * secondBar;
    double nFBar = forward * firstBar;
    double d1Bar = sign * NORMAL.getPDF(sign * d1) * nFBar;
    // Implementation Note: d2Bar = 0; no need to implement it.
    // Methodology Note: d2Bar is optimal exercise boundary. The derivative at the optimal point is 0.
    double sigmaRootTBar = d1Bar;
    double lognormalVolBar = Math.sqrt(timeToExpiry) * sigmaRootTBar;
    double timeToExpiryBar = 0.5 / Math.sqrt(timeToExpiry) * lognormalVol * sigmaRootTBar;
    return ValueDerivatives.of(price, DoubleArray.of(forwardBar, strikeBar, timeToExpiryBar, lognormalVolBar));
  }