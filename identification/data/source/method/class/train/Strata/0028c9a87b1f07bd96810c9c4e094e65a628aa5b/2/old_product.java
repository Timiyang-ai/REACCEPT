@Override
  public MultiCurrencyAmount currencyExposure(FxResetNotionalExchange event, RatesProvider provider) {
    double df = provider.discountFactor(event.getCurrency(), event.getPaymentDate());
    FxIndexRates rates = provider.fxIndexRates(event.getIndex());
    LocalDate maturityDate = rates.getIndex().calculateMaturityFromFixing(event.getFixingDate());
    double fxRateSpotSensitivity = rates.getFxForwardRates().rateFxSpotSensitivity(event.getReferenceCurrency(), maturityDate);
    return MultiCurrencyAmount.of(
        CurrencyAmount.of(event.getReferenceCurrency(), event.getNotional() * df * fxRateSpotSensitivity));
  }