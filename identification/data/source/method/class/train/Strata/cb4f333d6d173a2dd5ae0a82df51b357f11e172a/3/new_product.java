public CurrencyParameterSensitivity multipliedBy(Currency currency, double amount) {
    return CurrencyParameterSensitivity.of(
        marketDataName, parameterMetadata, currency, sensitivity.multipliedBy(amount), parameterSplit);
  }