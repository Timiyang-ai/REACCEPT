public FxForwardSensitivity toFxForwardSensitivity() {
    return FxForwardSensitivity.of(
        observation.getCurrencyPair(),
        referenceCurrency,
        observation.getMaturityDate(),
        currency,
        sensitivity);
  }