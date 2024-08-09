public double price(ResolvedBondFuture future, LegalEntityDiscountingProvider discountingProvider) {
    ImmutableList<ResolvedFixedCouponBond> basket = future.getDeliveryBasket();
    int size = basket.size();
    double[] priceBonds = new double[size];
    for (int i = 0; i < size; ++i) {
      ResolvedFixedCouponBond bond = basket.get(i);
      double dirtyPrice = bondPricer.dirtyPriceFromCurves(bond, discountingProvider, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond, future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactors().get(i);
    }
    return Doubles.min(priceBonds);
  }