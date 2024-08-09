public double theta(
      ResolvedFxSingleBarrierOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    ValueDerivatives priceDerivatives = priceDerivatives(option, ratesProvider, volatilities);
    return -priceDerivatives.getDerivative(5);
  }