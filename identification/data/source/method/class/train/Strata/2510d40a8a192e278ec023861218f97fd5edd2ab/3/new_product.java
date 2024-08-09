public CurrencyAmount presentValue(RatesProvider provider, SwapLeg leg, Currency currency) {
    double pv = legValue(provider, leg.expand(), paymentPeriodPricer::presentValue, paymentEventPricer::presentValue);
    return CurrencyAmount.of(currency, (pv * provider.fxRate(leg.getCurrency(), currency)));
  }