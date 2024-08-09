public static Frequency ofMonths(int months) {
    if (months > MAX_MONTHS) {
      throw new IllegalArgumentException("Months must not exceed 12,000");
    }
    return new Frequency(Period.ofMonths(months));
  }