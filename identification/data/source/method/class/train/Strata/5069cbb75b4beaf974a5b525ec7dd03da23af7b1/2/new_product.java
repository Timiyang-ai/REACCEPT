@Override
  public MultiCurrencyAmount get(int index) {
    List<CurrencyAmount> currencyAmounts = amounts.getCurrencies().stream()
        .map(ccy -> CurrencyAmount.of(ccy, amounts.getValues(ccy).get(index)))
        .collect(toList());
    return MultiCurrencyAmount.of(currencyAmounts);
  }