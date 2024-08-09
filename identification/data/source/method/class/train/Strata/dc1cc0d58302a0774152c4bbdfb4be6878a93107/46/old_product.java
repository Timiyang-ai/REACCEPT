public CurrencyAmount presentValue(PricingEnvironment env, FraProduct product) {
    // futureValue * discountFactor
    ExpandedFra fra = product.expand();
    double df = env.discountFactor(fra.getCurrency(), fra.getPaymentDate());
    double pv = futureValue(env, fra) * df;
    return CurrencyAmount.of(fra.getCurrency(), pv);
  }