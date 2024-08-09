public double zSpreadFromCurvesAndCleanPrice(
      Security<CapitalIndexedBond> security,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double cleanPrice,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    CapitalIndexedBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), REF_DATA);
    final Function<Double, Double> residual = new Function<Double, Double>() {
      @Override
      public Double apply(Double z) {
        double dirtyPrice = dirtyNominalPriceFromCurvesWithZSpread(
            security, ratesProvider, issuerDiscountFactorsProvider, settlementDate, z, compoundedRateType, periodsPerYear);
        if (product.getYieldConvention().equals(YieldConvention.INDEX_LINKED_FLOAT)) {
          return cleanNominalPriceFromDirtyNominalPrice(product, ratesProvider, settlementDate, dirtyPrice) - cleanPrice;
        }
        double dirtyRealPrice = realPriceFromNominalPrice(product, ratesProvider, settlementDate, dirtyPrice);
        return cleanRealPriceFromDirtyRealPrice(product, settlementDate, dirtyRealPrice) - cleanPrice;
      }
    };
    double[] range = ROOT_BRACKETER.getBracketedPoints(residual, -0.5, 0.5); // Starting range is [-1%, 1%]
    return ROOT_FINDER.getRoot(residual, range[0], range[1]);
  }