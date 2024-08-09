public double price(BondFuture future, LegalEntityDiscountingProvider provider) {
    ImmutableList<Security<FixedCouponBond>> bondSecurity = future.getBondSecurityBasket();
    int size = bondSecurity.size();
    double[] priceBonds = new double[size];
    for (int i = 0; i < size; ++i) {
      Security<FixedCouponBond> bond = bondSecurity.get(i);
      double dirtyPrice = bondPricer.dirtyPriceFromCurves(bond, provider, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond.getProduct(), future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactor().get(i);
    }
    final double priceFuture = Doubles.min(priceBonds);
    return priceFuture;
  }