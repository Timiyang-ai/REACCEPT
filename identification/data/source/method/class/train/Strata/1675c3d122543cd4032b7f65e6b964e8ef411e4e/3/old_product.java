public double convexityFromStandardYield(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LocalDate settlementDate,
      double yield) {

    Schedule scheduleUnadjusted = bond.getPeriodicSchedule().createSchedule(REF_DATA).toUnadjusted();
    int nbCoupon = bond.getPeriodicPayments().size();
    double couponPerYear = bond.getPeriodicSchedule().getFrequency().eventsPerYear();
    double factorOnPeriod = 1d + yield / couponPerYear;
    double cvAtFirstCoupon = 0;
    double pvAtFirstCoupon = 0;
    int pow = 0;
    double factorToNext = factorToNextCoupon(scheduleUnadjusted, bond.getDayCount(), settlementDate);
    for (int loopcpn = 0; loopcpn < nbCoupon; loopcpn++) {
      CapitalIndexedBondPaymentPeriod period = bond.getPeriodicPayments().get(loopcpn);
      if ((bond.hasExCouponPeriod() && !settlementDate.isAfter(period.getDetachmentDate())) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(settlementDate))) {
        cvAtFirstCoupon += period.getRealCoupon() * (pow + factorToNext) * (pow + factorToNext + 1d) /
            (Math.pow(factorOnPeriod, pow + 2) * couponPerYear * couponPerYear);
        pvAtFirstCoupon += period.getRealCoupon() / Math.pow(factorOnPeriod, pow);
        ++pow;
      }
    }
    cvAtFirstCoupon += (pow - 1d + factorToNext) *
        (pow + factorToNext) / (Math.pow(factorOnPeriod, pow + 1) * couponPerYear * couponPerYear);
    pvAtFirstCoupon += 1d / Math.pow(factorOnPeriod, pow - 1);
    double pv = pvAtFirstCoupon * Math.pow(factorOnPeriod, -factorToNext);
    double cv = cvAtFirstCoupon * Math.pow(factorOnPeriod, -factorToNext) / pv;
    return cv;
  }