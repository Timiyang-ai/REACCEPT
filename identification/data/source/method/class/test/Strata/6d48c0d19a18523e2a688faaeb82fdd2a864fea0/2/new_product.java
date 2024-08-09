public MultiCurrencyAmount currencyExposure(
      ResolvedFxSingleBarrierOption option,
      RatesProvider ratesProvider,
      BlackFxOptionVolatilities volatilities) {

    ResolvedFxVanillaOption underlyingOption = option.getUnderlyingOption();
    if (volatilities.relativeTime(underlyingOption.getExpiry()) < 0d) {
      return MultiCurrencyAmount.empty();
    }
    ValueDerivatives priceDerivatives = priceDerivatives(option, ratesProvider, volatilities);
    double price = priceDerivatives.getValue();
    double delta = priceDerivatives.getDerivative(0);
    CurrencyPair currencyPair = underlyingOption.getUnderlying().getCurrencyPair();
    double todayFx = ratesProvider.fxRate(currencyPair);
    double signedNotional = signedNotional(underlyingOption);
    CurrencyAmount domestic = CurrencyAmount.of(currencyPair.getCounter(), (price - delta * todayFx) * signedNotional);
    CurrencyAmount foreign = CurrencyAmount.of(currencyPair.getBase(), delta * signedNotional);
    return MultiCurrencyAmount.of(domestic, foreign);
  }