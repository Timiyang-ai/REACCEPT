public PointSensitivities priceSensitivity(PricingEnvironment env, IborFuture future) {
    IborRateSensitivity sensi = IborRateSensitivity.of(future.getIndex(), future.getFixingDate(), -1.0d);
    // The sensitivity should be to no currency or currency XXX. To avoid useless conversion, the dimension-less 
    // price sensitivity is reported in the future currency.
    return PointSensitivities.of(sensi);
  }