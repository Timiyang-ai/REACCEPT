public FxForwardSensitivity toFxForwardSensitivity() {
    LocalDate maturityDate = index.calculateMaturityFromFixing(fixingDate);
    return FxForwardSensitivity.of(index.getCurrencyPair(), referenceCurrency, maturityDate, currency, sensitivity);
  }