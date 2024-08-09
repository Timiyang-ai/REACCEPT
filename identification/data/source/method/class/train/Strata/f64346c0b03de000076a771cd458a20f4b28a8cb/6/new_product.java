public double yieldFromCurves(ResolvedBill bill, LegalEntityDiscountingProvider provider, LocalDate settlementDate) {
    double price = priceFromCurves(bill, provider, settlementDate);
    return bill.yieldFromPrice(price, settlementDate);
  }