@Override
  public CurrencyParameterSensitivities parameterSensitivity(ZeroRateSensitivity pointSens) {
    LocalDate date = pointSens.getDate();
    if (date.equals(valuationDate)) {
      return CurrencyParameterSensitivities.empty(); // Discount factor in 0 is always 1, no sensitivity.
    }
    double relativeYearFraction = relativeYearFraction(date);
    double discountFactor = discountFactor(relativeYearFraction);
    UnitParameterSensitivity unitSens = curve.yValueParameterSensitivity(relativeYearFraction);
    CurrencyParameterSensitivity curSens = unitSens
        .multipliedBy(-1d / (relativeYearFraction * discountFactor))
        .multipliedBy(pointSens.getCurrency(), pointSens.getSensitivity());
    return CurrencyParameterSensitivities.of(curSens);
  }