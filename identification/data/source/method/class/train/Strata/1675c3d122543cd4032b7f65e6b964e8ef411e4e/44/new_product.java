public double dirtyNominalPriceFromCurves(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    return dirtyNominalPriceFromCurves(bond, ratesProvider, discountingProvider, settlementDate);
  }