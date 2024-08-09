public double priceWithZSpread(
      ResolvedBondFuture future,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodPerYear) {

    ImmutableList<ResolvedFixedCouponBond> basket = future.getDeliveryBasket();
    int size = basket.size();
    double[] priceBonds = new double[size];
    for (int i = 0; i < size; ++i) {
      ResolvedFixedCouponBond bond = basket.get(i);
      double dirtyPrice = bondPricer.dirtyPriceFromCurvesWithZSpread(
          bond, provider, zSpread, compoundedRateType, periodPerYear, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond, future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactors().get(i);
    }
    return Doubles.min(priceBonds);
  }