public CurrencyAmount presentValue(PricingEnvironment env, IborFutureTrade trade, double referencePrice) {
    double price = price(env, trade);
    return presentValue(trade, price, referencePrice);
  }