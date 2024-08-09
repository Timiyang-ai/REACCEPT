public double realYieldFromCurves(
      Security<CapitalIndexedBond> security,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    CapitalIndexedBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), REF_DATA);
    double dirtyPrice;
    if (product.getYieldConvention().equals(YieldConvention.INDEX_LINKED_FLOAT)) {
      dirtyPrice = dirtyNominalPriceFromCurves(security, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
    } else {
      double dirtyNominalPrice =
          dirtyNominalPriceFromCurves(security, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
      dirtyPrice = realPriceFromNominalPrice(product, ratesProvider, settlementDate, dirtyNominalPrice);
    }
    return realYieldFromDirtyPrice(product, ratesProvider, settlementDate, dirtyPrice);
  }