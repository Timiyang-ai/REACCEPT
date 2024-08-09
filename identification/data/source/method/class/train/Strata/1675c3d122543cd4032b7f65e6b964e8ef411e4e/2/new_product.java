public double dirtyNominalPriceFromCurves(
      Security<CapitalIndexedBond> security,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    CapitalIndexedBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), REF_DATA);
    return dirtyNominalPriceFromCurves(security, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
  }