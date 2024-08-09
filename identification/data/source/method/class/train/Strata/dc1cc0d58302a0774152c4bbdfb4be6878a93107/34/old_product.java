@Override
  public double presentValue(RatePaymentPeriod period, RatesProvider provider) {
    // futureValue * discountFactor
    double df = provider.discountFactor(period.getCurrency(), period.getPaymentDate());
    return futureValue(period, provider) * df;
  }