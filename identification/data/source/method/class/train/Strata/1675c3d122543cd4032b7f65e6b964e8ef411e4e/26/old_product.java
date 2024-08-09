public double dirtyNominalPriceFromCurves(
      ResolvedCapitalIndexedBond bond,
      StandardId securityId,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), REF_DATA);
    return dirtyNominalPriceFromCurves(bond, securityId, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
  }