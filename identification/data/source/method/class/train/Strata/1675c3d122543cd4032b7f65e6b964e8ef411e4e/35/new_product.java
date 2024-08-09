public double modifiedDurationFromStandardYield(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LocalDate settlementDate,
      double yield) {

    int nbCoupon = bond.getPeriodicPayments().size();
    double couponPerYear = bond.getFrequency().eventsPerYear();
    double factorOnPeriod = 1d + yield / couponPerYear;
    double mdAtFirstCoupon = 0d;
    double pvAtFirstCoupon = 0d;
    int pow = 0;
    double factorToNext = factorToNextCoupon(bond, settlementDate);
    for (int loopcpn = 0; loopcpn < nbCoupon; loopcpn++) {
      CapitalIndexedBondPaymentPeriod period = bond.getPeriodicPayments().get(loopcpn);
      if ((bond.hasExCouponPeriod() && !settlementDate.isAfter(period.getDetachmentDate())) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(settlementDate))) {
        mdAtFirstCoupon += period.getRealCoupon() / Math.pow(factorOnPeriod, pow + 1) *
            (pow + factorToNext) / couponPerYear;
        pvAtFirstCoupon += period.getRealCoupon() / Math.pow(factorOnPeriod, pow);
        ++pow;
      }
    }
    mdAtFirstCoupon += (pow - 1d + factorToNext) / (couponPerYear * Math.pow(factorOnPeriod, pow));
    pvAtFirstCoupon += 1d / Math.pow(factorOnPeriod, pow - 1);
    double dp = pvAtFirstCoupon * Math.pow(factorOnPeriod, -factorToNext);
    double md = mdAtFirstCoupon * Math.pow(factorOnPeriod, -factorToNext) / dp;
    return md;
  }