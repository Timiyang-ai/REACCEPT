public static Frequency ofMonths(int months) {
    switch (months) {
      case 1:
        return P1M;
      case 2:
        return P2M;
      case 3:
        return P3M;
      case 4:
        return P4M;
      case 6:
        return P6M;
      case 12:
        return P12M;
      default:
        if (months > MAX_MONTHS) {
          throw new IllegalArgumentException(maxMonthMsg());
        }
        return new Frequency(Period.ofMonths(months));
    }
  }