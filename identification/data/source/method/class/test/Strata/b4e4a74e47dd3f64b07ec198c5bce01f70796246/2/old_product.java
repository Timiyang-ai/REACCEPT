@Override
  public Temporal subtractFrom(Temporal temporal) {
    // special case for performance
    if (temporal instanceof LocalDate) {
      LocalDate date = (LocalDate) temporal;
      return date.minusMonths(period.toTotalMonths()).minusDays(period.getDays());
    }
    return period.subtractFrom(temporal);
  }