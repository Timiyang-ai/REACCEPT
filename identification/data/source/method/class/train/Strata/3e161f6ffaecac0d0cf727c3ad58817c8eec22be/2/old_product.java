public static Frequency ofMonths(int months) {
    if (months > 12000) {
      throw new IllegalArgumentException("Months must not exceed 12000");
    }
    return new Frequency(Period.ofMonths(months));
  }