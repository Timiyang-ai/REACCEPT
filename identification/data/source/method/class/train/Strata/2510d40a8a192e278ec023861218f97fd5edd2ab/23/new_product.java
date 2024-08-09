public CurrencyAmount presentValue(ResolvedSwap swap, Currency currency, RatesProvider provider) {
    double totalPv = 0;
    for (ResolvedSwapLeg leg : swap.getLegs()) {
      double pv = legPricer.presentValueInternal(leg, provider);
      totalPv += (pv * provider.fxRate(leg.getCurrency(), currency));
    }
    return CurrencyAmount.of(currency, totalPv);
  }