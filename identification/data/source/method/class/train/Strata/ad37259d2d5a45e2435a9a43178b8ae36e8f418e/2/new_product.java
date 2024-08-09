public double priceFromCurves(ResolvedBill bill, LegalEntityDiscountingProvider provider, LocalDate settlementDate) {
    ArgChecker.inOrderNotEqual(settlementDate, bill.getNotional().getDate(), "settlementDate", "endDate");
    ArgChecker.inOrderOrEqual(provider.getValuationDate(), settlementDate, "valuationDate", "settlementDate");
    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bill, provider);
    double dfMaturity = issuerDf.discountFactor(bill.getNotional().getDate());
    RepoCurveDiscountFactors repoDf = repoCurveDf(bill, provider);
    double dfRepoSettle = repoDf.discountFactor(settlementDate);
    return dfMaturity / dfRepoSettle;
  }