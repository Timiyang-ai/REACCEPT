public double price(PricingEnvironment env, IborFutureTrade trade) {
    return productPricer.price(env, trade.getSecurity().getProduct());
  }