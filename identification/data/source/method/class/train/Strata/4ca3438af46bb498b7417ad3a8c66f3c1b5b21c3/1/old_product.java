public PointSensitivities forecastValueSensitivity(FraProduct product, RatesProvider provider) {
    ExpandedFra fra = product.expand();
    double notional = fra.getNotional();
    double derivative = derivative(fra, provider);
    PointSensitivityBuilder iborSens = forwardRateSensitivity(fra, provider)
        .multipliedBy(derivative * notional);
    return iborSens.withCurrency(fra.getCurrency()).build();
  }