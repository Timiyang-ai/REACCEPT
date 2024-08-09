public PointSensitivities presentValueSensitivity(FraProduct product, RatesProvider provider) {
    ExpandedFra fra = product.expand();
    double df = provider.discountFactor(fra.getCurrency(), fra.getPaymentDate());
    double notional = fra.getNotional();
    double unitAmount = unitAmount(fra, provider);
    double derivative = derivative(fra, provider);
    PointSensitivityBuilder iborSens = forwardRateSensitivity(fra, provider)
        .multipliedBy(derivative * df * notional);
    PointSensitivityBuilder discSens =
        provider.discountFactorZeroRateSensitivity(fra.getCurrency(), fra.getPaymentDate())
            .multipliedBy(unitAmount * notional);
    return iborSens.withCurrency(fra.getCurrency()).combinedWith(discSens).build();
  }