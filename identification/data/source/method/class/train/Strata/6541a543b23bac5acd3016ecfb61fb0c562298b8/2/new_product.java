@Override
  public PriceIndexValues priceIndexValues(PriceIndex index) {
    Curve curve = indexCurves.get(index);
    if (curve == null) {
      return historicCurve(index);
    }
    return PriceIndexValues.of(index, valuationDate, curve, timeSeries(index));
  }