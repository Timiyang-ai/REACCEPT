public static Tenor ofYears(int years) {
    if (years == 1) {
      return TENOR_12M;
    }
    return new Tenor(Period.ofYears(years), years + "Y");
  }