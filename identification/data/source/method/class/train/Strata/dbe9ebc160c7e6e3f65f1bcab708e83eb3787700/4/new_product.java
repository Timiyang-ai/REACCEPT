@Override
  public CurrencyParameterSensitivities parameterSensitivity(ZeroRateSensitivity pointSens) {
    LocalDate date = pointSens.getDate();
    if (date.equals(valuationDate)) {
      return CurrencyParameterSensitivities.empty(); // Discount factor in 0 is always 1, no sensitivity.
    }
    double yearFraction = relativeYearFraction(date);
    double discountFactor = discountFactor(yearFraction);
    UnitParameterSensitivity unitSens = curve.yValueParameterSensitivity(yearFraction);
    CurrencyParameterSensitivity curSens = unitSens
        .multipliedBy(-1d / (yearFraction * discountFactor))
        .multipliedBy(pointSens.getCurrency(), pointSens.getSensitivity());
    return CurrencyParameterSensitivities.of(curSens);
  }