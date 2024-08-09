public CurrencyAmount presentValue(FraProduct product, RatesProvider provider) {
    // forecastValue * discountFactor
    ExpandedFra fra = product.expand();
    double df = provider.discountFactor(fra.getCurrency(), fra.getPaymentDate());
    double pv = forecastValue0(fra, provider) * df;
    return CurrencyAmount.of(fra.getCurrency(), pv);
  }