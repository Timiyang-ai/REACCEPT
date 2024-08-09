@Override
  public double value(PriceIndexObservation observation) {
    YearMonth fixingMonth = observation.getFixingMonth();
    // returns the historic month price index if present in the time series
    OptionalDouble fixing = fixings.get(fixingMonth.atEndOfMonth());
    if (fixing.isPresent()) {
      return fixing.getAsDouble();
    }
    // otherwise, return the estimate from the curve.
    double nbMonth = numberOfMonths(fixingMonth);
    return curve.yValue(nbMonth);
  }