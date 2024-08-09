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
    double value = extendedCurve.yValue(nbMonth);
    int month0 = fixingMonth.getMonthValue() - 1; // seasonality list start at 0 and months start at 1
    double adjustment = seasonality.get(month0);
    return value * adjustment;
  }