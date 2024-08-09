public boolean contains(Currency currency) {
    ArgChecker.notNull(currency, "currency");
    return base.equals(currency) || counter.equals(currency);
  }