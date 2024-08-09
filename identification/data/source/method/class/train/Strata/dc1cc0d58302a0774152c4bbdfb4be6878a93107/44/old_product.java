@Override
  public double presentValue(PricingEnvironment env, FxResetNotionalExchange event) {
    // futureValue * discountFactor
    double df = env.discountFactor(event.getCurrency(), event.getPaymentDate());
    return futureValue(env, event) * df;
  }