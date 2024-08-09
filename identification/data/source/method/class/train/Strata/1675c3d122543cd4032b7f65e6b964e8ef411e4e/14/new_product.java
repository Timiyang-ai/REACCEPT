public double realYieldFromCurves(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    double dirtyPrice;
    if (bond.getYieldConvention().equals(CapitalIndexedBondYieldConvention.GB_IL_FLOAT)) {
      dirtyPrice = dirtyNominalPriceFromCurves(bond, ratesProvider, discountingProvider, settlementDate);
    } else {
      double dirtyNominalPrice =
          dirtyNominalPriceFromCurves(bond, ratesProvider, discountingProvider, settlementDate);
      dirtyPrice = realPriceFromNominalPrice(bond, ratesProvider, settlementDate, dirtyNominalPrice);
    }
    return realYieldFromDirtyPrice(bond, ratesProvider, settlementDate, dirtyPrice);
  }