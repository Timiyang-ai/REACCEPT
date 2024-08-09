static CurrencyAmount parseCurrencyAmount(CsvRow row, String currencyField, String amountField) {
    Currency currency = LoaderUtils.parseCurrency(row.getValue(currencyField));
    double amount = LoaderUtils.parseDouble(row.getValue(amountField));
    return CurrencyAmount.of(currency, amount);
  }