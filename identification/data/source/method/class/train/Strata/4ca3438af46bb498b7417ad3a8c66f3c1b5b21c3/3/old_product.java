public CurrencyAmount forecastValue(FraProduct product, RatesProvider provider) {
    ExpandedFra fra = product.expand();
    double fv = forecastValue0(fra, provider);
    return CurrencyAmount.of(fra.getCurrency(), fv);
  }