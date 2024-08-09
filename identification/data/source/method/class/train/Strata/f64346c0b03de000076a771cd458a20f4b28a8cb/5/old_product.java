public PointSensitivities presentValueSensitivity(ResolvedBill bill, LegalEntityDiscountingProvider provider) {
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bill.getLegalEntityId(), bill.getCurrency());
    double dfEndBar = bill.getNotional();
    PointSensitivityBuilder sensMaturity = discountFactors.zeroRatePointSensitivity(bill.getMaturityDate())
        .multipliedBy(dfEndBar);
    return sensMaturity.build();
  }