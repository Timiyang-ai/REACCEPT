public double price(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider discountingProvider,
      BlackBondFutureVolatilities volatilities) {

    double futurePrice = futurePrice(futureOption, discountingProvider);
    return price(futureOption, discountingProvider, volatilities, futurePrice);
  }