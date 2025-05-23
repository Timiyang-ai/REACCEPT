@Override
  public IborIndexRates iborIndexRates(IborIndex index) {
    LocalDateDoubleTimeSeries timeSeries = timeSeries(index);
    YieldCurve curve = indexCurve(index);
    DiscountFactors dfc = ZeroRateDiscountFactors.of(index.getCurrency(), getValuationDate(), dayCount, curve);
    return DiscountIborIndexRates.of(index, timeSeries, dfc);
  }