public double zSpreadFromCurvesAndCleanPrice(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double cleanPrice,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    final Function<Double, Double> residual = new Function<Double, Double>() {
      @Override
      public Double apply(Double z) {
        double dirtyPrice = dirtyNominalPriceFromCurvesWithZSpread(
            bond,
            ratesProvider,
            discountingProvider,
            settlementDate,
            z,
            compoundedRateType,
            periodsPerYear);
        if (bond.getYieldConvention().equals(CapitalIndexedBondYieldConvention.GB_IL_FLOAT)) {
          return cleanNominalPriceFromDirtyNominalPrice(bond, ratesProvider, settlementDate, dirtyPrice) - cleanPrice;
        }
        double dirtyRealPrice = realPriceFromNominalPrice(bond, ratesProvider, settlementDate, dirtyPrice);
        return cleanRealPriceFromDirtyRealPrice(bond, settlementDate, dirtyRealPrice) - cleanPrice;
      }
    };
    double[] range = ROOT_BRACKETER.getBracketedPoints(residual, -0.5, 0.5); // Starting range is [-1%, 1%]
    return ROOT_FINDER.getRoot(residual, range[0], range[1]);
  }