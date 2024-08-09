@Override
  public IborIndexRates iborIndexRates(IborIndex index) {
    LocalDateDoubleTimeSeries fixings = timeSeries(index);
    Curve curve = indexCurve(index);
    return IborIndexRates.of(index, valuationDate, curve, fixings);
  }