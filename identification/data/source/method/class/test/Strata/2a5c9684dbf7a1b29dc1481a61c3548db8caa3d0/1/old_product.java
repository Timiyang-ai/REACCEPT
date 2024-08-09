public static MultiCurrencyAmount total(Iterable<CurrencyAmount> amounts) {
    ArgChecker.notNull(amounts, "amounts");
    Map<Currency, CurrencyAmount> map = new HashMap<Currency, CurrencyAmount>();
    for (CurrencyAmount currencyAmount : amounts) {
      ArgChecker.notNull(currencyAmount, "amount");
      map.merge(currencyAmount.getCurrency(), currencyAmount, CurrencyAmount::plus);
    }
    return new MultiCurrencyAmount(ImmutableSortedSet.copyOf(map.values()));
  }