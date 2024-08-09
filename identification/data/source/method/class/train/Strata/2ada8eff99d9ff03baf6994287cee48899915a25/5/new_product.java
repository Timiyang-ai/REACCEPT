@Override
  public PointSensitivityBuilder ratePointSensitivity(LocalDate fixingDate) {
    LocalDate valuationDate = getValuationDate();
    if (fixingDate.isBefore(valuationDate) ||
        (fixingDate.equals(valuationDate) && fixings.get(fixingDate).isPresent())) {
      return PointSensitivityBuilder.none();
    }
    return IborRateSensitivity.of(index, fixingDate, 1d);
  }