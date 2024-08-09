@Override
  public double[] unitParameterSensitivity(LocalDate date) {
    return discountFactors.unitParameterSensitivity(date);
  }