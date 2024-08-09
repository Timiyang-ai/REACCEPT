public double realYieldFromCurves(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    double dirtyPrice;
    if (bond.getYieldConvention().equals(CapitalIndexedBondYieldConvention.GB_IL_FLOAT)) {
      dirtyPrice = dirtyNominalPriceFromCurves(bond, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
    } else {
      double dirtyNominalPrice =
          dirtyNominalPriceFromCurves(bond, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
      dirtyPrice = realPriceFromNominalPrice(bond, ratesProvider, settlementDate, dirtyNominalPrice);
    }
    return realYieldFromDirtyPrice(bond, ratesProvider, settlementDate, dirtyPrice);
  }