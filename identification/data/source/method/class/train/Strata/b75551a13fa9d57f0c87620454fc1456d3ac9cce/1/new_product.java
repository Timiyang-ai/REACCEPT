@Override
  public double value(PriceIndexObservation observation) {
    YearMonth fixingMonth = observation.getFixingMonth();
    // If fixing in the past, check time series and returns the historic month price index if present
    if (fixingMonth.isBefore(YearMonth.from(valuationDate))) {
      OptionalDouble fixing = fixings.get(fixingMonth.atEndOfMonth());
      if (fixing.isPresent()) {
        return fixing.getAsDouble();
      }
    }
    // otherwise, return the estimate from the curve.
    double nbMonth = numberOfMonths(fixingMonth);
    return curve.yValue(nbMonth);
  }