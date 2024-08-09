public CurrencyAmount forecastValue(ResolvedFra fra, RatesProvider provider) {
    double fv = forecastValue0(fra, provider);
    return CurrencyAmount.of(fra.getCurrency(), fv);
  }