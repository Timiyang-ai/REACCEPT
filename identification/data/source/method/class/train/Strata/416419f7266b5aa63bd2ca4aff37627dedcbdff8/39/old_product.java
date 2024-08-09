public BondFutureOptionSensitivity priceSensitivityBlackVolatility(
      BondFutureOption futureOption,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider,
      double futurePrice) {

    ArgChecker.isTrue(futureOption.getPremiumStyle().equals(FutureOptionPremiumStyle.DAILY_MARGIN),
        "Premium style should be DAILY_MARGIN");
    ArgChecker.isTrue(futureOption.getUnderlyingLink().getStandardId().equals(volatilityProvider.getFutureSecurityId()),
        "Underlying future security ID should be the same as security ID of data");
    double strike = futureOption.getStrikePrice();
    BondFuture future = futureOption.getUnderlying();
    double volatility = volatilityProvider.getVolatility(futureOption.getExpiry(),
        future.getLastTradeDate(), strike, futurePrice);
    double timeToExpiry = volatilityProvider.relativeTime(futureOption.getExpiry());
    double vega = BlackFormulaRepository.vega(futurePrice, strike, timeToExpiry, volatility);
    return BondFutureOptionSensitivity.of(futureOption.getUnderlyingLink().getStandardId(),
        futureOption.getExpiry(),
        future.getLastTradeDate(), strike, futurePrice, future.getCurrency(), vega);
  }