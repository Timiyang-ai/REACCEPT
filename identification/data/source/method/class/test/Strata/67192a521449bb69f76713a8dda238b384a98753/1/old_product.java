public static Collector<CurrencyAmount, ?, MultiCurrencyAmount> collector() {
    return Collector.<CurrencyAmount, Map<Currency, CurrencyAmount>, MultiCurrencyAmount>of(
            // accumulate into a map
            HashMap::new,
            // merge two CurrencyAmounts if same currency
            (map, ca) -> map.merge(ArgChecker.notNull(ca, "amount").getCurrency(), ca, CurrencyAmount::plus),
            // combine two maps
            (map1, map2) -> {
              map1.putAll(map2);
              return map1;
            },
            // convert to MultiCurrencyAmount
            (map) -> new MultiCurrencyAmount(ImmutableSortedSet.copyOf(map.values())),
            UNORDERED);
  }