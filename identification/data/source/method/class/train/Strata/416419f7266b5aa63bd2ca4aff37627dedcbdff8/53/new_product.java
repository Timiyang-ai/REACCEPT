public PointSensitivities priceSensitivity(ResolvedBondFuture future, LegalEntityDiscountingProvider discountingProvider) {
    ImmutableList<ResolvedFixedCouponBond> basket = future.getDeliveryBasket();
    int size = basket.size();
    double[] priceBonds = new double[size];
    int indexCTD = 0;
    double priceMin = 2d;
    for (int i = 0; i < size; i++) {
      ResolvedFixedCouponBond bond = basket.get(i);
      double dirtyPrice = bondPricer.dirtyPriceFromCurves(bond, discountingProvider, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond, future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactors().get(i);
      if (priceBonds[i] < priceMin) {
        priceMin = priceBonds[i];
        indexCTD = i;
      }
    }
    ResolvedFixedCouponBond bond = basket.get(indexCTD);
    PointSensitivityBuilder pointSensi = bondPricer.dirtyPriceSensitivity(
        bond, discountingProvider, future.getLastDeliveryDate());
    return pointSensi.multipliedBy(1d / future.getConversionFactors().get(indexCTD)).build();
  }