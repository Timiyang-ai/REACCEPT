public CurrencyAmount presentValue(ResolvedBill bill, LegalEntityDiscountingProvider provider) {
    if (provider.getValuationDate().isAfter(bill.getNotional().getDate())) {
      return CurrencyAmount.of(bill.getCurrency(), 0.0d);
    }
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bill.getLegalEntityId(), bill.getCurrency());
    double dfMaturity = discountFactors.discountFactor(bill.getNotional().getDate());
    return bill.getNotional().getValue().multipliedBy(dfMaturity);
  }