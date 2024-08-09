@Override
  public PriceIndexValues priceIndexValues(PriceIndex index) {
    LocalDateDoubleTimeSeries fixings = timeSeries(index);
    Curve curve = indexCurve(index);
    return PriceIndexValues.of(index, valuationDate, curve, fixings);
  }