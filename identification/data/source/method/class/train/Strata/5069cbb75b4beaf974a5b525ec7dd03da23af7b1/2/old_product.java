@Override
  public MultiCurrencyAmount get(int index) {
    List<CurrencyAmount> currencyAmounts = values.keySet().stream()
        .map(ccy -> CurrencyAmount.of(ccy, values.get(ccy).get(index)))
        .collect(toList());
    return MultiCurrencyAmount.of(currencyAmounts);
  }