public double theta(
      ResolvedBondFutureOption futureOption,
      LegalEntityDiscountingProvider discountingProvider,
      BlackBondFutureVolatilities volatilities,
      double futurePrice) {

    ArgChecker.isTrue(futureOption.getPremiumStyle().equals(FutureOptionPremiumStyle.DAILY_MARGIN),
        "Premium style should be DAILY_MARGIN");
    double strike = futureOption.getStrikePrice();
    ResolvedBondFuture future = futureOption.getUnderlyingFuture();
    double volatility = volatilities.volatility(futureOption.getExpiry(),
        future.getLastTradeDate(), strike, futurePrice);
    double timeToExpiry = volatilities.relativeTime(futureOption.getExpiry());
    double theta = BlackFormulaRepository.driftlessTheta(futurePrice, strike, timeToExpiry, volatility);
    return theta;
  }