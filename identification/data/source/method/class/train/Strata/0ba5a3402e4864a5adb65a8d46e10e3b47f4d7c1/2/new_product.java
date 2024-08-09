static CurrencyAmount parseCurrencyAmount(CsvRow row, String currencyField, String amountField) {
    Currency currency = row.getValue(currencyField, LoaderUtils::parseCurrency);
    double amount = row.getValue(amountField, LoaderUtils::parseDouble);
    return CurrencyAmount.of(currency, amount);
  }