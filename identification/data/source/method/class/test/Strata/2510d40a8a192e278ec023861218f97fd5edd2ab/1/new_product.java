public CurrencyAmount presentValue(RatesProvider provider, SwapProduct product, Currency currency) {
    double totalPv = 0;
    for (ExpandedSwapLeg leg : product.expand().getLegs()) {
      double pv = legPricer.presentValueInternal(provider, leg);
      totalPv += (pv * provider.fxRate(leg.getCurrency(), currency));
    }
    return CurrencyAmount.of(currency, totalPv);
  }