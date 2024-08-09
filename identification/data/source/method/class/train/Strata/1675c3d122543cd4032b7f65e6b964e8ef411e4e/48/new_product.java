public double dirtyPriceFromStandardYield(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LocalDate settlementDate,
      double yield) {

    int nbCoupon = bond.getPeriodicPayments().size();
    double couponPerYear = bond.getFrequency().eventsPerYear();
    double factorOnPeriod = 1d + yield / couponPerYear;
    double pvAtFirstCoupon = 0d;
    int pow = 0;
    double factorToNext = factorToNextCoupon(bond, settlementDate);
    for (int loopcpn = 0; loopcpn < nbCoupon; loopcpn++) {
      CapitalIndexedBondPaymentPeriod period = bond.getPeriodicPayments().get(loopcpn);
      if ((bond.hasExCouponPeriod() && !settlementDate.isAfter(period.getDetachmentDate())) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(settlementDate))) {
        pvAtFirstCoupon += period.getRealCoupon() / Math.pow(factorOnPeriod, pow);
        ++pow;
      }
    }
    pvAtFirstCoupon += 1d / Math.pow(factorOnPeriod, pow - 1);
    return pvAtFirstCoupon * Math.pow(factorOnPeriod, -factorToNext);
  }