public PointSensitivities priceSensitivityWithZSpread(
      ResolvedBondFuture future,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodPerYear) {

    ImmutableList<Pair<ResolvedFixedCouponBond, StandardId>> basket = future.getDeliveryBasket();
    int size = basket.size();
    double[] priceBonds = new double[size];
    int indexCTD = 0;
    double priceMin = 2d;
    for (int i = 0; i < size; i++) {
      Pair<ResolvedFixedCouponBond, StandardId> bondSec = basket.get(i);
      ResolvedFixedCouponBond bond = bondSec.getFirst();
      double dirtyPrice = bondPricer.dirtyPriceFromCurvesWithZSpread(
          bond, bondSec.getSecond(), provider, zSpread, compoundedRateType, periodPerYear, future.getLastDeliveryDate());
      priceBonds[i] = bondPricer.cleanPriceFromDirtyPrice(
          bond, future.getLastDeliveryDate(), dirtyPrice) / future.getConversionFactors().get(i);
      if (priceBonds[i] < priceMin) {
        priceMin = priceBonds[i];
        indexCTD = i;
      }
    }
    Pair<ResolvedFixedCouponBond, StandardId> bondSec = basket.get(indexCTD);
    ResolvedFixedCouponBond bond = bondSec.getFirst();
    PointSensitivityBuilder pointSensi = bondPricer.dirtyPriceSensitivityWithZspread(
        bond, bondSec.getSecond(), provider, zSpread, compoundedRateType, periodPerYear, future.getLastDeliveryDate());
    return pointSensi.multipliedBy(1d / future.getConversionFactors().get(indexCTD)).build();
  }