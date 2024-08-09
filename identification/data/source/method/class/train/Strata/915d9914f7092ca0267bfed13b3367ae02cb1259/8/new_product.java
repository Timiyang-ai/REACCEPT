@Override
  public double discountFactor(LocalDate date) {
    double relativeYearFraction = relativeYearFraction(date);
    return discountFactor(relativeYearFraction);
  }