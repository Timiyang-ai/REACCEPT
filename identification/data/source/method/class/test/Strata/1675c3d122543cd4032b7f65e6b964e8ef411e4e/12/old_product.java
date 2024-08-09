public double dirtyPriceFromStandardYield(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LocalDate settlementDate,
      double yield) {

    ExpandedCapitalIndexedBond expanded = product.expand();
    Schedule scheduleUnadjusted = product.getPeriodicSchedule().createSchedule().toUnadjusted();
    int nbCoupon = expanded.getPeriodicPayments().size();
    double couponPerYear = product.getPeriodicSchedule().getFrequency().eventsPerYear();
    double factorOnPeriod = 1d + yield / couponPerYear;
    double pvAtFirstCoupon = 0d;
    int pow = 0;
    double factorToNext = factorToNextCoupon(scheduleUnadjusted, product.getDayCount(), settlementDate);
    for (int loopcpn = 0; loopcpn < nbCoupon; loopcpn++) {
      CapitalIndexedBondPaymentPeriod period = expanded.getPeriodicPayments().get(loopcpn);
      if ((product.getExCouponPeriod().getDays() != 0 && !settlementDate.isAfter(period.getDetachmentDate())) ||
          (product.getExCouponPeriod().getDays() == 0 && period.getPaymentDate().isAfter(settlementDate))) {
        pvAtFirstCoupon += period.getRealCoupon() / Math.pow(factorOnPeriod, pow);
        ++pow;
      }
    }
    pvAtFirstCoupon += 1d / Math.pow(factorOnPeriod, pow - 1);
    return pvAtFirstCoupon * Math.pow(factorOnPeriod, -factorToNext);
  }