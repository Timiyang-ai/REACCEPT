public double priceWithZSpread(
      BondFuture future,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodPerYear) {

    ImmutableList<Security<FixedCouponBond>> bondSecurity = future.getBondSecurityBasket();
    int size = bondSecurity.size();
    double[] priceBonds = new double[size];
    for (int i = 0; i < size; ++i) {
      Security<FixedCouponBond> bond = bondSecurity.get(i);
      double dirtyPrice = bondPricer.dirtyPriceFromCurvesWithZSpread(
          bond, provider, zSpread, compoundedRateType, periodPerYear, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond.getProduct(), future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactor().get(i);
    }
    return Doubles.min(priceBonds);
  }