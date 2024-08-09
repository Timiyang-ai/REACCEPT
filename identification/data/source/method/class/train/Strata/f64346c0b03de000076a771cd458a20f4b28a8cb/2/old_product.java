public CurrencyAmount presentValue(ResolvedBill bill, LegalEntityDiscountingProvider provider) {
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bill.getLegalEntityId(), bill.getCurrency());
    double dfMaturity = discountFactors.discountFactor(bill.getMaturityDate());
    return CurrencyAmount.of(bill.getCurrency(), dfMaturity * bill.getNotional());
  }