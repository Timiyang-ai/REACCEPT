public double theta(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackBondFutureVolatilities volatilities) {

    double futurePrice = futurePrice(futureOption, ratesProvider);
    return theta(futureOption, ratesProvider, volatilities, futurePrice);
  }