@Override
  public double presentValue(RatePaymentPeriod period, RatesProvider provider) {
    // forecastValue * discountFactor
    double df = provider.discountFactor(period.getCurrency(), period.getPaymentDate());
    return forecastValue(period, provider) * df;
  }