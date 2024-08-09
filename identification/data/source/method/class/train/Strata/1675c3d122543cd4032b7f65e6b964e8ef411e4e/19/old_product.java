public double modifiedDurationFromStandardYield(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LocalDate settlementDate,
      double yield) {

    ResolvedCapitalIndexedBond expanded = product.resolve(REF_DATA);
    Schedule scheduleUnadjusted = product.getPeriodicSchedule().createSchedule(REF_DATA).toUnadjusted();
    int nbCoupon = expanded.getPeriodicPayments().size();
    double couponPerYear = product.getPeriodicSchedule().getFrequency().eventsPerYear();
    double factorOnPeriod = 1d + yield / couponPerYear;
    double mdAtFirstCoupon = 0d;
    double pvAtFirstCoupon = 0d;
    int pow = 0;
    double factorToNext = factorToNextCoupon(scheduleUnadjusted, product.getDayCount(), settlementDate);
    for (int loopcpn = 0; loopcpn < nbCoupon; loopcpn++) {
      CapitalIndexedBondPaymentPeriod period = expanded.getPeriodicPayments().get(loopcpn);
      if ((product.getExCouponPeriod().getDays() != 0 && !settlementDate.isAfter(period.getDetachmentDate())) ||
          (product.getExCouponPeriod().getDays() == 0 && period.getPaymentDate().isAfter(settlementDate))) {
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