public SimplePriceIndexValues withCurve(NodalCurve curve) {
    return new SimplePriceIndexValues(index, valuationDate, curve, fixings);
  }