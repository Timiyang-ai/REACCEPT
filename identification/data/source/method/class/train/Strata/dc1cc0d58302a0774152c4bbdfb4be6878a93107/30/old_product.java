@Override
  public double presentValue(PricingEnvironment env, RatePaymentPeriod period) {
    // futureValue * discountFactor
    double df = env.discountFactor(period.getCurrency(), period.getPaymentDate());
    return futureValue(env, period) * df;
  }