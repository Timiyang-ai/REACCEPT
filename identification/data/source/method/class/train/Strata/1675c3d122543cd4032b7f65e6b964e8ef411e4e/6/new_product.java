public double realYieldFromCurves(
      ResolvedCapitalIndexedBond bond,
      StandardId securityId,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    double dirtyPrice;
    if (bond.getYieldConvention().equals(CapitalIndexedBondYieldConvention.INDEX_LINKED_FLOAT)) {
      dirtyPrice = dirtyNominalPriceFromCurves(bond, securityId, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
    } else {
      double dirtyNominalPrice =
          dirtyNominalPriceFromCurves(bond, securityId, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
      dirtyPrice = realPriceFromNominalPrice(bond, ratesProvider, settlementDate, dirtyNominalPrice);
    }
    return realYieldFromDirtyPrice(bond, ratesProvider, settlementDate, dirtyPrice);
  }