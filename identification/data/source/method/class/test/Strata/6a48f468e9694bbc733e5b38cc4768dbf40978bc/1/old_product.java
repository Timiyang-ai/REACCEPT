public MultiCurrencyAmount currencyExposure(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {
    double price = price(option, ratesProvider, volatilityProvider);
    double delta = delta(option, ratesProvider, volatilityProvider);
    double spot = ratesProvider.fxRate(option.getStrike().getPair());
    double signedNotional = signedNotional(option);
    CurrencyAmount domestic = CurrencyAmount.of(option.getPayoffCurrency(), (price - delta * spot) * signedNotional);
    CurrencyAmount foreign = CurrencyAmount.of(
        option.getUnderlying().getReceiveCurrencyAmount().getCurrency(), delta * signedNotional);
    return MultiCurrencyAmount.of(domestic,foreign);
  }