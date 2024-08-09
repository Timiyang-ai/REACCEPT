@Override
  public double presentValue(RatesProvider provider, RatePaymentPeriod period) {
    // futureValue * discountFactor
    double df = provider.discountFactor(period.getCurrency(), period.getPaymentDate());
    return futureValue(provider, period) * df;
  }