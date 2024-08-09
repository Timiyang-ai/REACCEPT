public boolean contains(Currency currency) {
    return base.equals(currency) || counter.equals(currency);
  }