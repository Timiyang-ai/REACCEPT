public double dirtyNominalPriceFromCurvesWithZSpread(
      Security<CapitalIndexedBond> security,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    CapitalIndexedBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), REF_DATA);
    return dirtyNominalPriceFromCurvesWithZSpread(security, ratesProvider, issuerDiscountFactorsProvider,
        settlementDate, zSpread, compoundedRateType, periodsPerYear);
  }