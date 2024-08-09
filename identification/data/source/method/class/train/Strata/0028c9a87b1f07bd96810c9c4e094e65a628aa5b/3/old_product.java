@Override
  public MultiCurrencyAmount currencyExposure(FxResetNotionalExchange event, RatesProvider provider) {
    FxIndexRates rates = provider.fxIndexRates(event.getIndex());
    double df = provider.discountFactor(event.getCurrency(), event.getPaymentDate());
    if (!event.getFixingDate().isAfter(provider.getValuationDate()) &&
        rates.getFixings().get(event.getFixingDate()).isPresent()) {
      double fxRate = rates.rate(event.getReferenceCurrency(), event.getFixingDate());
      return MultiCurrencyAmount.of(CurrencyAmount.of(event.getCurrency(), event.getNotional() * df * fxRate));
    }
    LocalDate maturityDate = rates.getIndex().calculateMaturityFromFixing(event.getFixingDate());
    double fxRateSpotSensitivity = rates.getFxForwardRates().rateFxSpotSensitivity(event.getReferenceCurrency(), maturityDate);
    return MultiCurrencyAmount.of(
        CurrencyAmount.of(event.getReferenceCurrency(), event.getNotional() * df * fxRateSpotSensitivity));
  }