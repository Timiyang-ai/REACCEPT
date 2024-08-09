public PointSensitivities presentValueSensitivity(PricingEnvironment env, FraProduct product) {
    ExpandedFra fra = product.expand();
    double df = env.discountFactor(fra.getCurrency(), fra.getPaymentDate());
    double notional = fra.getNotional();
    double unitAmount = unitAmount(env, fra);
    double derivative = derivative(env, fra);
    PointSensitivityBuilder iborSens = forwardRateSensitivity(env, fra)
        .multipliedBy(derivative * df * notional);
    PointSensitivityBuilder discSens =
        env.discountFactorZeroRateSensitivity(fra.getCurrency(), fra.getPaymentDate())
            .multipliedBy(unitAmount * notional);
    return iborSens.withCurrency(fra.getCurrency()).combinedWith(discSens).build();
  }