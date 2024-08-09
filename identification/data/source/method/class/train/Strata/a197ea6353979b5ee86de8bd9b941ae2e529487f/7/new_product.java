public double accruedInterest(ResolvedFixedCouponBond bond, LocalDate settlementDate) {
    double notional = bond.getNotional();
    return accruedYearFraction(bond, settlementDate) * bond.getFixedRate() * notional;
  }