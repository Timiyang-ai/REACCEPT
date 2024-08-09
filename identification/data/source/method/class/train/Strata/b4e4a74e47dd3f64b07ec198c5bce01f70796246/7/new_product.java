@Override
  public Temporal addTo(Temporal temporal) {
    // special case for performance
    if (temporal instanceof LocalDate) {
      LocalDate date = (LocalDate) temporal;
      return plusDays(date.plusMonths(period.toTotalMonths()), period.getDays());
    }
    return period.addTo(temporal);
  }