@Override
  public IborIndexRates iborIndexRates(IborIndex index) {
    LocalDateDoubleTimeSeries timeSeries = timeSeries(index);
    Curve curve = indexCurve(index);
    DiscountFactors dfc = ZeroRateDiscountFactors.of(index.getCurrency(), getValuationDate(), curve);
    return DiscountIborIndexRates.of(index, timeSeries, dfc);
  }