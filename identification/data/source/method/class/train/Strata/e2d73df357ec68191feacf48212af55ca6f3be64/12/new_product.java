public double price(ResolvedBondFuture future, LegalEntityDiscountingProvider provider) {
    ImmutableList<Pair<ResolvedFixedCouponBond, StandardId>> basket = future.getDeliveryBasket();
    int size = basket.size();
    double[] priceBonds = new double[size];
    for (int i = 0; i < size; ++i) {
      Pair<ResolvedFixedCouponBond, StandardId> bondSec = basket.get(i);
      ResolvedFixedCouponBond bond = bondSec.getFirst();
      double dirtyPrice = bondPricer.dirtyPriceFromCurves(
          bond, bondSec.getSecond(), provider, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond, future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactor().get(i);
    }
    return Doubles.min(priceBonds);
  }