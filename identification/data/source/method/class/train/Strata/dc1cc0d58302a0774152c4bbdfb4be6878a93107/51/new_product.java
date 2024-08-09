public PointSensitivities presentValueSensitivity(ResolvedFra fra, RatesProvider provider) {
    if (fra.getPaymentDate().isBefore(provider.getValuationDate())) {
      return PointSensitivities.empty();
    }
    DiscountFactors discountFactors = provider.discountFactors(fra.getCurrency());
    double df = discountFactors.discountFactor(fra.getPaymentDate());
    double notional = fra.getNotional();
    double unitAmount = unitAmount(fra, provider);
    double derivative = derivative(fra, provider);
    PointSensitivityBuilder iborSens = forwardRateSensitivity(fra, provider)
        .multipliedBy(derivative * df * notional);
    PointSensitivityBuilder discSens = discountFactors.zeroRatePointSensitivity(fra.getPaymentDate())
        .multipliedBy(unitAmount * notional);
    return iborSens.withCurrency(fra.getCurrency()).combinedWith(discSens).build();
  }