public double priceFromCurves(ResolvedBill bill, LegalEntityDiscountingProvider provider, LocalDate settlementDate) {
    ArgChecker.inOrderNotEqual(settlementDate, bill.getNotional().getDate(), "settlementDate", "endDate");
    ArgChecker.inOrderOrEqual(provider.getValuationDate(), settlementDate, "valuationDate", "settlementDate");
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bill.getLegalEntityId(), bill.getCurrency());
    double dfMaturity = discountFactors.discountFactor(bill.getNotional().getDate());
    RepoCurveDiscountFactors discountFactorsRepo =
        provider.repoCurveDiscountFactors(bill.getSecurityId(), bill.getLegalEntityId(), bill.getCurrency());
    double dfRepoSettle = discountFactorsRepo.discountFactor(settlementDate);
    return dfMaturity / dfRepoSettle;
  }