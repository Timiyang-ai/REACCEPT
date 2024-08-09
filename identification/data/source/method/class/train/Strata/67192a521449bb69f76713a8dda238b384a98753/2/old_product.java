public static Collector<CurrencyAmount, ?, MultiCurrencyAmount> collector() {
    return Collectors.collectingAndThen(
        Guavate.toImmutableSortedSet(),
        MultiCurrencyAmount::new);
  }