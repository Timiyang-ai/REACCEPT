public double dirtyPriceFromCleanPrice(FixedCouponBond product, LocalDate settlementDate, double cleanPrice) {
    double notional = product.getNotional();
    double accruedInterest = accruedInterest(product, settlementDate);
    return cleanPrice + accruedInterest / notional;
  }