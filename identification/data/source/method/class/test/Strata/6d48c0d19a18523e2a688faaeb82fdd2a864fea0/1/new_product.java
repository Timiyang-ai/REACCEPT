public double delta(
      ResolvedFxSingleBarrierOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    if (volatilities.relativeTime(option.getUnderlyingOption().getExpiry()) < 0d) {
      return 0d;
    }
    ValueDerivatives priceDerivatives = priceDerivatives(option, ratesProvider, volatilities);
    return priceDerivatives.getDerivative(0);
  }