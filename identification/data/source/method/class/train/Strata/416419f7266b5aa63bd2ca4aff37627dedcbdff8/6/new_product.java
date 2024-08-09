public double dirtyPriceFromCleanPrice(ResolvedFixedCouponBond bond, LocalDate settlementDate, double cleanPrice) {
    double notional = bond.getNotional();
    double accruedInterest = accruedInterest(bond, settlementDate);
    return cleanPrice + accruedInterest / notional;
  }