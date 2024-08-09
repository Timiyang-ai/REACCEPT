public static Tenor ofYears(int years) {
    return new Tenor(Period.ofYears(years), years + "Y");
  }