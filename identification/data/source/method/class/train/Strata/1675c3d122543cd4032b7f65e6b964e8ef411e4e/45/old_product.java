public double zSpreadFromCurvesAndCleanPrice(
      ResolvedCapitalIndexedBond bond,
      StandardId securityId,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double cleanPrice,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    final Function<Double, Double> residual = new Function<Double, Double>() {
      @Override
      public Double apply(Double z) {
        double dirtyPrice = dirtyNominalPriceFromCurvesWithZSpread(
            bond,
            securityId,
            ratesProvider,
            issuerDiscountFactorsProvider,
            settlementDate,
            z,
            compoundedRateType,
            periodsPerYear);
        if (bond.getYieldConvention().equals(YieldConvention.INDEX_LINKED_FLOAT)) {
          return cleanNominalPriceFromDirtyNominalPrice(bond, ratesProvider, settlementDate, dirtyPrice) - cleanPrice;
        }
        double dirtyRealPrice = realPriceFromNominalPrice(bond, ratesProvider, settlementDate, dirtyPrice);
        return cleanRealPriceFromDirtyRealPrice(bond, settlementDate, dirtyRealPrice) - cleanPrice;
      }
    };
    double[] range = ROOT_BRACKETER.getBracketedPoints(residual, -0.5, 0.5); // Starting range is [-1%, 1%]
    return ROOT_FINDER.getRoot(residual, range[0], range[1]);
  }