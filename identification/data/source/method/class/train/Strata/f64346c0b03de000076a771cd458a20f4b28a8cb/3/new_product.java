public PointSensitivities presentValueSensitivity(ResolvedBill bill, LegalEntityDiscountingProvider provider) {
    if (provider.getValuationDate().isAfter(bill.getNotional().getDate())) {
      return PointSensitivities.empty();
    }
    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bill, provider);
    double dfEndBar = bill.getNotional().getAmount();
    PointSensitivityBuilder sensMaturity = issuerDf.zeroRatePointSensitivity(bill.getNotional().getDate())
        .multipliedBy(dfEndBar);
    return sensMaturity.build();
  }