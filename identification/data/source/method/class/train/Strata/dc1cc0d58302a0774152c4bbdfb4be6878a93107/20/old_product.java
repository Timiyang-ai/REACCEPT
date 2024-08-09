public CurrencyAmount presentValue(FraProduct product, RatesProvider provider) {
    // futureValue * discountFactor
    ExpandedFra fra = product.expand();
    double df = provider.discountFactor(fra.getCurrency(), fra.getPaymentDate());
    double pv = futureValue(fra, provider) * df;
    return CurrencyAmount.of(fra.getCurrency(), pv);
  }