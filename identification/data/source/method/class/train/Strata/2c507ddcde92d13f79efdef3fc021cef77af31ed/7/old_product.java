public static Tenor ofMonths(int months) {
    if (months < 24) {
      return new Tenor(Period.ofMonths(months), months + "M");
    }
    Period period = Period.of(months / 12, months % 12, 0);
    return new Tenor(period, period.toString().substring(1));
  }