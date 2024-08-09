@Override
  public Temporal addTo(Temporal temporal) {
    // special case for performance
    if (temporal instanceof LocalDate) {
      LocalDate date = (LocalDate) temporal;
      return date.plusMonths(period.toTotalMonths()).plusDays(period.getDays());
    }
    return period.addTo(temporal);
  }