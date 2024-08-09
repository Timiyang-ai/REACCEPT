@Override
  public double discountFactor(LocalDate date) {
    return discountFactor(relativeTime(date));
  }