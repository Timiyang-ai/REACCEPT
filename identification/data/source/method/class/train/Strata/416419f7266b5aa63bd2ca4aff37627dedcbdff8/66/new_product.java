public BondFutureOptionSensitivity priceSensitivityModelParamsVolatility(
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
    double volatility = volatilityProvider.volatility(futureOption.getExpiry(),
        future.getLastTradeDate(), strike, futurePrice);
    double timeToExpiry = volatilityProvider.relativeTime(futureOption.getExpiry());
    double vega = BlackFormulaRepository.vega(futurePrice, strike, timeToExpiry, volatility);
    return BondFutureOptionSensitivity.of(
        futureOption.getUnderlyingFuture().getSecurityId(),
        futureOption.getExpiry(),
        future.getLastTradeDate(), strike, futurePrice, future.getCurrency(), vega);
  }