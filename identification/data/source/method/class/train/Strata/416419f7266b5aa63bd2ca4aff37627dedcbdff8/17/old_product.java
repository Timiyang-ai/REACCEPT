public double price(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider,
      double futurePrice) {

    ArgChecker.isTrue(futureOption.getPremiumStyle().equals(FutureOptionPremiumStyle.DAILY_MARGIN),
        "Premium style should be DAILY_MARGIN");
    ArgChecker.isTrue(
        futureOption.getUnderlyingFuture().getSecurityId().equals(volatilityProvider.getFutureSecurityId()),
        "Underlying future security ID should be the same as security ID of data");
    double strike = futureOption.getStrikePrice();
    ResolvedBondFuture future = futureOption.getUnderlyingFuture();
    double volatility = volatilityProvider.getVolatility(
        futureOption.getExpiry(), future.getLastTradeDate(), strike, futurePrice);
    double timeToExpiry = volatilityProvider.relativeTime(futureOption.getExpiry());
    double price = BlackFormulaRepository.price(
        futurePrice, strike, timeToExpiry, volatility, futureOption.getPutCall().isCall());
    return price;
  }