public double convexityFromStandardYield(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LocalDate settlementDate,
      double yield) {

    ExpandedCapitalIndexedBond expanded = product.expand();
    Schedule scheduleUnadjusted = product.getPeriodicSchedule().createSchedule().toUnadjusted();
    int nbCoupon = expanded.getPeriodicPayments().size();
    double couponPerYear = product.getPeriodicSchedule().getFrequency().eventsPerYear();
    double factorOnPeriod = 1d + yield / couponPerYear;
    double cvAtFirstCoupon = 0;
    double pvAtFirstCoupon = 0;
    int pow = 0;
    double factorToNext = factorToNextCoupon(scheduleUnadjusted, product.getDayCount(), settlementDate);
    for (int loopcpn = 0; loopcpn < nbCoupon; loopcpn++) {
      CapitalIndexedBondPaymentPeriod period = expanded.getPeriodicPayments().get(loopcpn);
      if ((product.getExCouponPeriod().getDays() != 0 && !settlementDate.isAfter(period.getDetachmentDate())) ||
          (product.getExCouponPeriod().getDays() == 0 && period.getPaymentDate().isAfter(settlementDate))) {
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