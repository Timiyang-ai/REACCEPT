public MultiCurrencyAmount presentValue(PricingEnvironment env, SwapProduct product) {
    return swapValue(env, product.expand(), paymentPeriodPricer::presentValue, paymentEventPricer::presentValue);
  }