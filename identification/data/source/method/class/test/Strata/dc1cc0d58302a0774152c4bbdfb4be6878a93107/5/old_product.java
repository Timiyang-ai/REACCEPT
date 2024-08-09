public double parSpread(PricingEnvironment env, IborFutureTrade trade, double referencePrice) {
    return price(env, trade) - referencePrice;
  }