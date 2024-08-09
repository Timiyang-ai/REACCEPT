@Override
  public FxForwardRates fxForwardRates(CurrencyPair currencyPair) {
    DiscountFactors base = discountFactors(currencyPair.getBase());
    DiscountFactors counter = discountFactors(currencyPair.getCounter());
    return DiscountFxForwardRates.of(currencyPair, fxRateProvider, base, counter);
  }