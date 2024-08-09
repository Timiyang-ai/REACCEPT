public double dirtyNominalPriceFromCurves(
      ResolvedCapitalIndexedBond bond,
      StandardId securityId,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    return dirtyNominalPriceFromCurves(bond, securityId, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
  }