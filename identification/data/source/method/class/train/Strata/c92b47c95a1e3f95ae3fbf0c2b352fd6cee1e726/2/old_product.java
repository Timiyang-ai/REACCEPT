public SimplePriceIndexValues withCurve(InterpolatedNodalCurve curve) {
    return new SimplePriceIndexValues(index, valuationDate, curve, fixings, seasonality);
  }