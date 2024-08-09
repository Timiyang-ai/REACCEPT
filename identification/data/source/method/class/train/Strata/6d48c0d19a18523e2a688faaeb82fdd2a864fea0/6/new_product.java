public double vega(
      ResolvedFxSingleBarrierOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    ValueDerivatives priceDerivatives = priceDerivatives(option, ratesProvider, volatilities);
    return priceDerivatives.getDerivative(4);
  }