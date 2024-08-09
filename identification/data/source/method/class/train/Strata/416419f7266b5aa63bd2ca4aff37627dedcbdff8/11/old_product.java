public PointSensitivities priceSensitivity(BondFuture future, LegalEntityDiscountingProvider provider) {
    ImmutableList<Security<FixedCouponBond>> bondSecurity = future.getBondSecurityBasket();
    int size = bondSecurity.size();
    double[] priceBonds = new double[size];
    int indexCTD = 0;
    double priceMin = 2d;
    for (int i = 0; i < size; i++) {
      Security<FixedCouponBond> bond = bondSecurity.get(i);
      double dirtyPrice = bondPricer.dirtyPriceFromCurves(bond, provider, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond.getProduct(), future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactor().get(i);
      if (priceBonds[i] < priceMin) {
        priceMin = priceBonds[i];
        indexCTD = i;
      }
    }
    PointSensitivityBuilder pointSensi = bondPricer.dirtyPriceSensitivity(
        bondSecurity.get(indexCTD), provider, future.getLastDeliveryDate());
    return pointSensi.multipliedBy(1d / future.getConversionFactor().get(indexCTD)).build();
  }