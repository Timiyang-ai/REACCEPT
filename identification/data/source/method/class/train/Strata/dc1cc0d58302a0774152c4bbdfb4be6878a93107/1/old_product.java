@Override
  public double presentValue(PricingEnvironment env, NotionalExchange event) {
    // futureValue * discountFactor
    double df = env.discountFactor(event.getPaymentAmount().getCurrency(), event.getPaymentDate());
    return futureValue(env, event) * df;
  }