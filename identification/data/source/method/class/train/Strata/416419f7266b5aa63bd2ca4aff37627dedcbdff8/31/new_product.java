public PointSensitivities priceSensitivity(ResolvedBondFuture future, LegalEntityDiscountingProvider provider) {
    ImmutableList<Pair<ResolvedFixedCouponBond, StandardId>> basket = future.getDeliveryBasket();
    int size = basket.size();
    double[] priceBonds = new double[size];
    int indexCTD = 0;
    double priceMin = 2d;
    for (int i = 0; i < size; i++) {
      Pair<ResolvedFixedCouponBond, StandardId> bondSec = basket.get(i);
      ResolvedFixedCouponBond bond = bondSec.getFirst();
      double dirtyPrice = bondPricer.dirtyPriceFromCurves(
          bond, bondSec.getSecond(), provider, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond, future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactors().get(i);
      if (priceBonds[i] < priceMin) {
        priceMin = priceBonds[i];
        indexCTD = i;
      }
    }
    Pair<ResolvedFixedCouponBond, StandardId> bondSec = basket.get(indexCTD);
    ResolvedFixedCouponBond bond = bondSec.getFirst();
    PointSensitivityBuilder pointSensi = bondPricer.dirtyPriceSensitivity(
        bond, bondSec.getSecond(), provider, future.getLastDeliveryDate());
    return pointSensi.multipliedBy(1d / future.getConversionFactors().get(indexCTD)).build();
  }