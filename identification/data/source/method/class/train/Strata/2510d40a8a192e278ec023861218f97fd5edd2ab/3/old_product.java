public CurrencyAmount presentValue(PricingEnvironment env, SwapLeg leg, Currency currency) {
    double pv = legValue(env, leg.expand(), paymentPeriodPricer::presentValue, paymentEventPricer::presentValue);
    return CurrencyAmount.of(currency, (pv * env.fxRate(leg.getCurrency(), currency)));
  }