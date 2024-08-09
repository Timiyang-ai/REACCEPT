public double delta(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    ResolvedFxSingle underlying = option.getUnderlying();
    double fwdDelta = undiscountedDelta(option, ratesProvider, volatilities);
    double discountFactor = ratesProvider.discountFactor(option.getCounterCurrency(), underlying.getPaymentDate());
    double fwdRateSpotSensitivity = fxPricer.forwardFxRateSpotSensitivity(
        option.getPutCall().isCall() ? underlying : underlying.inverse(), ratesProvider);
    return fwdDelta * discountFactor * fwdRateSpotSensitivity;
  }