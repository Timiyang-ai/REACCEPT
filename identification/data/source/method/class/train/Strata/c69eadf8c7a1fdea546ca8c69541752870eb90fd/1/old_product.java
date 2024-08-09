@Override
  public double[] parameterSensitivity(LocalDate date) {
    return discountFactors.parameterSensitivity(date);
  }