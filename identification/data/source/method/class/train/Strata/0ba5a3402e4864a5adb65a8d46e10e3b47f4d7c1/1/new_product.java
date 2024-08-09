static CurrencyAmount parseCurrencyAmountWithDirection(
      CsvRow row,
      String currencyField,
      String amountField,
      String directionField) {

    Currency currency = row.getValue(currencyField, LoaderUtils::parseCurrency);
    double amount = row.getValue(amountField, LoaderUtils::parseDouble);
    PayReceive direction = row.getValue(directionField, LoaderUtils::parsePayReceive);
    return CurrencyAmount.of(currency, direction.normalize(amount));
  }