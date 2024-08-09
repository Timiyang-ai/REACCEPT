public PointSensitivities forecastValueSensitivity(ResolvedFra fra, RatesProvider provider) {
    if (fra.getPaymentDate().isBefore(provider.getValuationDate())) {
      return PointSensitivities.empty();
    }
    double notional = fra.getNotional();
    double derivative = derivative(fra, provider);
    PointSensitivityBuilder iborSens = forwardRateSensitivity(fra, provider)
        .multipliedBy(derivative * notional);
    return iborSens.withCurrency(fra.getCurrency()).build();
  }