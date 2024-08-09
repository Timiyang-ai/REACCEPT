public PointSensitivities presentValueSensitivity(RatesProvider provider, FraProduct product) {
    ExpandedFra fra = product.expand();
    double df = provider.discountFactor(fra.getCurrency(), fra.getPaymentDate());
    double notional = fra.getNotional();
    double unitAmount = unitAmount(provider, fra);
    double derivative = derivative(provider, fra);
    PointSensitivityBuilder iborSens = forwardRateSensitivity(provider, fra)
        .multipliedBy(derivative * df * notional);
    PointSensitivityBuilder discSens =
        provider.discountFactorZeroRateSensitivity(fra.getCurrency(), fra.getPaymentDate())
            .multipliedBy(unitAmount * notional);
    return iborSens.withCurrency(fra.getCurrency()).combinedWith(discSens).build();
  }