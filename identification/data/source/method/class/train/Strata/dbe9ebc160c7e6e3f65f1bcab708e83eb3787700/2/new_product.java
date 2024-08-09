@Override
  public CurrencyParameterSensitivities parameterSensitivity(ZeroRateSensitivity pointSens) {
    double yearFraction = relativeYearFraction(pointSens.getDate());
    double rp = curve.yValue(yearFraction);
    double rcBar = 1.0;
    double rpBar = 1.0 / (1 + rp / frequency) * rcBar;
    UnitParameterSensitivity unitSens = curve.yValueParameterSensitivity(yearFraction).multipliedBy(rpBar);
    CurrencyParameterSensitivity curSens = unitSens.multipliedBy(pointSens.getCurrency(), pointSens.getSensitivity());
    return CurrencyParameterSensitivities.of(curSens);
  }