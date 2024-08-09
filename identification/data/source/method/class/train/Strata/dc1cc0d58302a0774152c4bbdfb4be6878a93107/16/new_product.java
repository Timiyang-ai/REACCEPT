public CurrencyAmount presentValue(ResolvedFra fra, RatesProvider provider) {
    // forecastValue * discountFactor
    double df = provider.discountFactor(fra.getCurrency(), fra.getPaymentDate());
    double pv = forecastValue0(fra, provider) * df;
    return CurrencyAmount.of(fra.getCurrency(), pv);
  }