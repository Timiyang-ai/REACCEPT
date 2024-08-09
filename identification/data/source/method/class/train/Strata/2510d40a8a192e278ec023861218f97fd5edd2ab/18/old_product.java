public CurrencyAmount presentValue(PricingEnvironment env, SwapLeg leg) {
    return CurrencyAmount.of(leg.getCurrency(), presentValueInternal(env, leg));
  }