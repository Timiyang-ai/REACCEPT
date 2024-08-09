public static Frequency ofMonths(int months) {
    if (months > 12000) {
      throw new IllegalArgumentException("Months must not exceed 12000");
    }
    if (months % 12 == 0) {
      return ofYears(months / 12);
    }
    return new Frequency(Period.ofMonths(months));
  }