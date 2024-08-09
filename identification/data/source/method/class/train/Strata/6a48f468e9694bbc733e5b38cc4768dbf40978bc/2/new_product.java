public MultiCurrencyAmount currencyExposure(
      ResolvedFxVanillaOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    CurrencyPair strikePair = option.getUnderlying().getCurrencyPair();
    double price = price(option, ratesProvider, volatilities);
    double delta = delta(option, ratesProvider, volatilities);
    double spot = ratesProvider.fxRate(strikePair);
    double signedNotional = signedNotional(option);
    CurrencyAmount domestic = CurrencyAmount.of(strikePair.getCounter(), (price - delta * spot) * signedNotional);
    CurrencyAmount foreign = CurrencyAmount.of(strikePair.getBase(), delta * signedNotional);
    return MultiCurrencyAmount.of(domestic, foreign);
  }