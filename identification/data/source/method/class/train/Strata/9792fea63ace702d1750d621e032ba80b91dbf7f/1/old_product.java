public boolean contains(@Nullable Currency currency) {
    return amounts.stream().anyMatch(ca -> ca.getCurrency().equals(currency));
  }