public PointSensitivities priceSensitivity(ResolvedIborFuture future, RatesProvider provider) {
    IborRateSensitivity sensi = IborRateSensitivity.of(future.getObservation(), -1d);
    // The sensitivity should be to no currency or currency XXX. To avoid useless conversion, the dimension-less 
    // price sensitivity is reported in the future currency.
    return PointSensitivities.of(sensi);
  }