@Override
  public OvernightIndexRates overnightIndexRates(OvernightIndex index) {
    LocalDateDoubleTimeSeries fixings = timeSeries(index);
    Curve curve = indexCurve(index);
    return OvernightIndexRates.of(index, valuationDate, curve, fixings);
  }