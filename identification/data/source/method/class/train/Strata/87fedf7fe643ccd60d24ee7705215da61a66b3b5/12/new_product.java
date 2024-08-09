@Override
  public PointSensitivityBuilder ratePointSensitivity(OvernightIndexObservation observation) {
    LocalDate valuationDate = getValuationDate();
    LocalDate fixingDate = observation.getFixingDate();
    LocalDate publicationDate = observation.getPublicationDate();
    if (publicationDate.isBefore(valuationDate) ||
        (publicationDate.equals(valuationDate) && fixings.get(fixingDate).isPresent())) {
      return PointSensitivityBuilder.none();
    }
    return OvernightRateSensitivity.of(observation, 1d);
  }