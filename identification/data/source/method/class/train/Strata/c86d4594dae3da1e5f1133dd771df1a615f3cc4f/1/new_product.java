public MultiCurrencyAmount total() {
    return sensitivities.stream()
        .map(CurrencyParameterSensitivity::total)
        .collect(toMultiCurrencyAmount());
  }