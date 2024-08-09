public CurrencyAmount presentValue(ResolvedSwapLeg leg, Currency currency, RatesProvider provider) {
    double pv = presentValueInternal(leg, provider);
    return CurrencyAmount.of(currency, (pv * provider.fxRate(leg.getCurrency(), currency)));
  }