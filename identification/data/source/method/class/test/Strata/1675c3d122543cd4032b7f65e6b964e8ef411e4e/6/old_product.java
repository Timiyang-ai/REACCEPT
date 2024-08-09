public double realYieldFromCurves(
      ResolvedCapitalIndexedBond bond,
      StandardId securityId,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), REF_DATA);
    double dirtyPrice;
    if (bond.getYieldConvention().equals(YieldConvention.INDEX_LINKED_FLOAT)) {
      dirtyPrice = dirtyNominalPriceFromCurves(bond, securityId, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
    } else {
      double dirtyNominalPrice =
          dirtyNominalPriceFromCurves(bond, securityId, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
      dirtyPrice = realPriceFromNominalPrice(bond, ratesProvider, settlementDate, dirtyNominalPrice);
    }
    return realYieldFromDirtyPrice(bond, ratesProvider, settlementDate, dirtyPrice);
  }