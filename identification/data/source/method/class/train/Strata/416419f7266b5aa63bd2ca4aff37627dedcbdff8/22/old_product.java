public double price(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackBondFutureVolatilities volatilities) {

    double futurePrice = futurePrice(futureOption, ratesProvider);
    return price(futureOption, ratesProvider, volatilities, futurePrice);
  }