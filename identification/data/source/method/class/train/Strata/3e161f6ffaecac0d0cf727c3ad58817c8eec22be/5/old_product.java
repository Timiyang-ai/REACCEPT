public static Frequency ofYears(int years) {
    if (years > MAX_YEARS) {
      throw new IllegalArgumentException("Years must not exceed 1,000");
    }
    return new Frequency(Period.ofYears(years));
  }