public CurrencyAmount presentValue(ResolvedBill bill, LegalEntityDiscountingProvider provider) {
    if (provider.getValuationDate().isAfter(bill.getNotional().getDate())) {
      return CurrencyAmount.of(bill.getCurrency(), 0.0d);
    }
    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bill, provider);
    double dfMaturity = issuerDf.discountFactor(bill.getNotional().getDate());
    return bill.getNotional().getValue().multipliedBy(dfMaturity);
  }