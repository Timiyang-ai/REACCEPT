@Override
  public IborIndexRates iborIndexRates(IborIndex index) {
    Curve curve = indexCurves.get(index);
    if (curve == null) {
      return historicCurve(index);
    }
    return IborIndexRates.of(index, valuationDate, curve, timeSeries(index));
  }