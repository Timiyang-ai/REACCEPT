public boolean contains(@Nullable Currency currency) {
    return base.equals(currency) || counter.equals(currency);
  }