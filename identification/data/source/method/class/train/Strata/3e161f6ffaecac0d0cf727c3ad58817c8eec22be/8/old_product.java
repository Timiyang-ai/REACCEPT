public static Frequency ofYears(int years) {
    if (years > 1000) {
      throw new IllegalArgumentException("Years must not exceed 1000");
    }
    return new Frequency(Period.ofYears(years));
  }