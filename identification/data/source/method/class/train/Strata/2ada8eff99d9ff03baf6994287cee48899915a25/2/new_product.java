@Override
  public PointSensitivityBuilder ratePointSensitivity(IborRateObservation observation) {
    LocalDate fixingDate = observation.getFixingDate();
    LocalDate valuationDate = getValuationDate();
    if (fixingDate.isBefore(valuationDate) ||
        (fixingDate.equals(valuationDate) && fixings.get(fixingDate).isPresent())) {
      return PointSensitivityBuilder.none();
    }
    return IborRateSensitivity.of(observation, 1d);
  }