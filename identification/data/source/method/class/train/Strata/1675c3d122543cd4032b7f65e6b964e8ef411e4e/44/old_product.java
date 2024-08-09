public double dirtyNominalPriceFromCurves(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    return dirtyNominalPriceFromCurves(bond, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
  }