public PointSensitivities presentValueSensitivity(ResolvedBill bill, LegalEntityDiscountingProvider provider) {
    if (provider.getValuationDate().isAfter(bill.getNotional().getDate())) {
      return PointSensitivities.empty();
    }
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bill.getLegalEntityId(), bill.getCurrency());
    double dfEndBar = bill.getNotional().getAmount();
    PointSensitivityBuilder sensMaturity = discountFactors.zeroRatePointSensitivity(bill.getNotional().getDate())
        .multipliedBy(dfEndBar);
    return sensMaturity.build();
  }