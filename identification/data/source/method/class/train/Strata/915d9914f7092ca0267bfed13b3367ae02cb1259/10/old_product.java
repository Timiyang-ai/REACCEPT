@Override
  public IborIndexRates iborIndexRates(IborIndex index) {
    LocalDateDoubleTimeSeries timeSeries = timeSeries(index);
    Curve curve = indexCurve(index);
    DiscountFactors dfc = DiscountFactors.of(index.getCurrency(), getValuationDate(), curve);
    return DiscountIborIndexRates.of(index, timeSeries, dfc);
  }