public double priceWithZSpread(
      ResolvedBondFuture future,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodPerYear) {

    ImmutableList<Pair<ResolvedFixedCouponBond, StandardId>> basket = future.getDeliveryBasket();
    int size = basket.size();
    double[] priceBonds = new double[size];
    for (int i = 0; i < size; ++i) {
      Pair<ResolvedFixedCouponBond, StandardId> bondSec = basket.get(i);
      ResolvedFixedCouponBond bond = bondSec.getFirst();
      double dirtyPrice = bondPricer.dirtyPriceFromCurvesWithZSpread(
          bond, bondSec.getSecond(), provider, zSpread, compoundedRateType, periodPerYear, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond, future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactors().get(i);
    }
    return Doubles.min(priceBonds);
  }