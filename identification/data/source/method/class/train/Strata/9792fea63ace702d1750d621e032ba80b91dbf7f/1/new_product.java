public boolean contains(Currency currency) {
    ArgChecker.notNull(currency, "currency");
    return amounts.stream().anyMatch(ca -> ca.getCurrency().equals(currency));
  }