@Override
  public PointSensitivityBuilder valuePointSensitivity(PriceIndexObservation observation) {
    YearMonth fixingMonth = observation.getFixingMonth();
    // If fixing in the past, check time series and returns the historic month price index if present
    if (fixingMonth.isBefore(YearMonth.from(valuationDate))) {
      if (fixings.get(fixingMonth.atEndOfMonth()).isPresent()) {
        return PointSensitivityBuilder.none();
      }
    }
    return InflationRateSensitivity.of(observation, 1d);
  }