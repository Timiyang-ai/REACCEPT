public CurrencyAmount presentValue(PricingEnvironment env, SwapProduct product, Currency currency) {
    double totalPv = 0;
    for (ExpandedSwapLeg leg : product.expand().getLegs()) {
      double pv = legPricer.presentValueInternal(env, leg);
      totalPv += (pv * env.fxRate(leg.getCurrency(), currency));
    }
    return CurrencyAmount.of(currency, totalPv);
  }