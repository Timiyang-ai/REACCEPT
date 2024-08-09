@Override
  public MultiCurrencyAmount currencyExposure(FxResetNotionalExchange event, RatesProvider provider) {
    LocalDate fixingDate = event.getObservation().getFixingDate();
    FxIndexRates rates = provider.fxIndexRates(event.getObservation().getIndex());
    double df = provider.discountFactor(event.getCurrency(), event.getPaymentDate());
    if (!fixingDate.isAfter(provider.getValuationDate()) &&
        rates.getFixings().get(fixingDate).isPresent()) {
      double fxRate = rates.rate(event.getObservation(), event.getReferenceCurrency());
      return MultiCurrencyAmount.of(CurrencyAmount.of(event.getCurrency(), event.getNotional() * df * fxRate));
    }
    LocalDate maturityDate = event.getObservation().getMaturityDate();
    double fxRateSpotSensitivity =
        rates.getFxForwardRates().rateFxSpotSensitivity(event.getReferenceCurrency(), maturityDate);
    return MultiCurrencyAmount.of(
        CurrencyAmount.of(event.getReferenceCurrency(), event.getNotional() * df * fxRateSpotSensitivity));
  }