@Override
  public OvernightIndexRates overnightIndexRates(OvernightIndex index) {
    Curve curve = indexCurves.get(index);
    if (curve == null) {
      return historicCurve(index);
    }
    return OvernightIndexRates.of(index, valuationDate, curve, timeSeries(index));
  }