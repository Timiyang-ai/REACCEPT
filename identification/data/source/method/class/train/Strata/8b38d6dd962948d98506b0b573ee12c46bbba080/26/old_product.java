public PointSensitivities priceSensitivity(
      IborFuture future,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider) {

    IborIndex index = future.getIndex();
    LocalDate fixingStartDate = index.calculateEffectiveFromFixing(future.getFixingDate());
    LocalDate fixingEndDate = index.calculateMaturityFromEffective(fixingStartDate);
    double convexity = hwProvider.futuresConvexityFactor(future.getLastTradeDate(), fixingStartDate, fixingEndDate);
    IborRateSensitivity sensi = IborRateSensitivity.of(future.getIndex(), future.getFixingDate(), -convexity);
    // The sensitivity should be to no currency or currency XXX. To avoid useless conversion, the dimension-less 
    // price sensitivity is reported in the future currency.
    return PointSensitivities.of(sensi);
  }