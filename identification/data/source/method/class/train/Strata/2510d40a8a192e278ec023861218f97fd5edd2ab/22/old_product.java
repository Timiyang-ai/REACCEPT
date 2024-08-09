public CurrencyAmount presentValue(SwapLeg leg, Currency currency, RatesProvider provider) {
    double pv = legValue(leg.expand(), provider, paymentPeriodPricer::presentValue, paymentEventPricer::presentValue);
    return CurrencyAmount.of(currency, (pv * provider.fxRate(leg.getCurrency(), currency)));
  }