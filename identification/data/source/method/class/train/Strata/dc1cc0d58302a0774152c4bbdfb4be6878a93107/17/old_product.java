public double price(PricingEnvironment env, IborFuture future) {
    double forward = env.iborIndexRate(future.getIndex(), future.getFixingDate());
    return 1.0 - forward;
  }