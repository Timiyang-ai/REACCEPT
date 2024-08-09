static CurrencyAmount parseCurrencyAmountWithDirection(
      CsvRow row,
      String currencyField,
      String amountField,
      String directionField) {

    Currency currency = LoaderUtils.parseCurrency(row.getValue(currencyField));
    double amount = LoaderUtils.parseDouble(row.getValue(amountField));
    PayReceive direction = LoaderUtils.parsePayReceive(row.getValue(directionField));
    return CurrencyAmount.of(currency, direction.normalize(amount));
  }