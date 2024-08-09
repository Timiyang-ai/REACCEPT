public double yieldFromCurves(ResolvedBill bill, LegalEntityDiscountingProvider provider, LocalDate settlementDate) {
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bill.getLegalEntityId(), bill.getCurrency());
    double dfMaturity = discountFactors.discountFactor(bill.getMaturityDate());
    RepoCurveDiscountFactors discountFactorsRepo =
        provider.repoCurveDiscountFactors(bill.getSecurityId(), bill.getLegalEntityId(), bill.getCurrency());
    double dfRepoSettle = discountFactorsRepo.discountFactor(settlementDate);
    double settleAmount = bill.getNotional() * dfMaturity / dfRepoSettle;
    return yieldFromSettlementAmount(bill, Payment.of(bill.getCurrency(), settleAmount, settlementDate));
  }