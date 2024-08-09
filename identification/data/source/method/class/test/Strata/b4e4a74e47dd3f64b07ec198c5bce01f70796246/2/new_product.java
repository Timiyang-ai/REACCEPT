@Override
  public Temporal subtractFrom(Temporal temporal) {
    // special case for performance
    if (temporal instanceof LocalDate) {
      LocalDate date = (LocalDate) temporal;
      return plusDays(date.minusMonths(period.toTotalMonths()), -period.getDays());
    }
    return period.subtractFrom(temporal);
  }