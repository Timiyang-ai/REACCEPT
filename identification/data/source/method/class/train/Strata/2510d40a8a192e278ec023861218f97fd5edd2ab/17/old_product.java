public CurrencyAmount presentValue(PricingEnvironment env, SwapLeg leg) {
    double val = legValue(env, leg.expand(), paymentPeriodPricer::presentValue, paymentEventPricer::presentValue);
    return CurrencyAmount.of(leg.getCurrency(), val);
  }