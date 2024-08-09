public CurrencyAmount presentValue(SwapProduct product, Currency currency, RatesProvider provider) {
    double totalPv = 0;
    for (ExpandedSwapLeg leg : product.expand().getLegs()) {
      double pv = legPricer.presentValueInternal(leg, provider);
      totalPv += (pv * provider.fxRate(leg.getCurrency(), currency));
    }
    return CurrencyAmount.of(currency, totalPv);
  }