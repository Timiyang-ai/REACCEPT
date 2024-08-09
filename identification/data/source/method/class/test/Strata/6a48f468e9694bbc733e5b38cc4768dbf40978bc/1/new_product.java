public MultiCurrencyAmount currencyExposure(
      FxVanillaOption option,
      RatesProvider ratesProvider,
      BlackVolatilityFxProvider volatilityProvider) {
    CurrencyPair strikePair = option.getStrike().getPair();
    double price = price(option, ratesProvider, volatilityProvider);
    double delta = delta(option, ratesProvider, volatilityProvider);
    double spot = ratesProvider.fxRate(strikePair);
    double signedNotional = signedNotional(option);
    CurrencyAmount domestic = CurrencyAmount.of(option.getPayoffCurrency(), (price - delta * spot) * signedNotional);
    CurrencyAmount foreign = CurrencyAmount.of(strikePair.getBase(), delta * signedNotional);
    return MultiCurrencyAmount.of(domestic, foreign);
  }