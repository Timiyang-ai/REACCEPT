@Override
  public PointSensitivityBuilder valuePointSensitivity(PriceIndexObservation observation) {
    YearMonth fixingMonth = observation.getFixingMonth();
    // no sensitivity if historic month price index present in the time series
    if (fixings.get(fixingMonth.atEndOfMonth()).isPresent()) {
      return PointSensitivityBuilder.none();
    }
    return InflationRateSensitivity.of(observation, 1d);
  }