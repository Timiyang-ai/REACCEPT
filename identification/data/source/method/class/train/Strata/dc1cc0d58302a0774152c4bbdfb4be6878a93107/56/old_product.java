public CurrencyAmount presentValue(RatesProvider provider, FraProduct product) {
    // futureValue * discountFactor
    ExpandedFra fra = product.expand();
    double df = provider.discountFactor(fra.getCurrency(), fra.getPaymentDate());
    double pv = futureValue(provider, fra) * df;
    return CurrencyAmount.of(fra.getCurrency(), pv);
  }