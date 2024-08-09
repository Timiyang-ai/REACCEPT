@Override
  public CurrencyParameterSensitivities parameterSensitivity(ZeroRateSensitivity pointSens) {
    double yearFraction = pointSens.getYearFraction();
    if (Math.abs(yearFraction) < EFFECTIVE_ZERO) {
      return CurrencyParameterSensitivities.empty(); // Discount factor in 0 is always 1, no sensitivity.
    }
    double discountFactor = discountFactor(yearFraction);
    UnitParameterSensitivity unitSens = curve.yValueParameterSensitivity(yearFraction);
    CurrencyParameterSensitivity curSens = unitSens
        .multipliedBy(-1d / (yearFraction * discountFactor))
        .multipliedBy(pointSens.getCurrency(), pointSens.getSensitivity());
    return CurrencyParameterSensitivities.of(curSens);
  }