public PointSensitivities priceSensitivityWithZSpread(
      BondFuture future,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodPerYear) {

    ImmutableList<Security<FixedCouponBond>> bondSecurity = future.getBondSecurityBasket();
    int size = bondSecurity.size();
    double[] priceBonds = new double[size];
    int indexCTD = 0;
    double priceMin = 2d;
    for (int i = 0; i < size; i++) {
      Security<FixedCouponBond> bond = bondSecurity.get(i);
      double dirtyPrice = bondPricer.dirtyPriceFromCurvesWithZSpread(
          bond, provider, zSpread, compoundedRateType, periodPerYear, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond.getProduct(), future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactor().get(i);
      if (priceBonds[i] < priceMin) {
        priceMin = priceBonds[i];
        indexCTD = i;
      }
    }
    PointSensitivityBuilder pointSensi = bondPricer.dirtyPriceSensitivityWithZspread(
        bondSecurity.get(indexCTD), provider, zSpread, compoundedRateType, periodPerYear, future.getLastDeliveryDate());
    return pointSensi.multipliedBy(1d / future.getConversionFactor().get(indexCTD)).build();
  }