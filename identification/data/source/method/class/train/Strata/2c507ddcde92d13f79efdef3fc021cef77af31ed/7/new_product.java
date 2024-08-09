public static Tenor ofMonths(int months) {
    return new Tenor(Period.ofMonths(months), months + "M");
  }