public double theta(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider discountingProvider,
      BlackBondFutureVolatilities volatilities) {

    double futurePrice = futurePrice(futureOption, discountingProvider);
    return theta(futureOption, discountingProvider, volatilities, futurePrice);
  }