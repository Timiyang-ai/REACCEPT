public static Frequency ofYears(int years) {
    if (years > MAX_YEARS) {
      throw new IllegalArgumentException(maxYearMsg());
    }
    return new Frequency(Period.ofYears(years));
  }