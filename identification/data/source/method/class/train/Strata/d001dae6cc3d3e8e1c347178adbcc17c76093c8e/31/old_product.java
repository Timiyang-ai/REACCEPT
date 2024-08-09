PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    IssuerCurveDiscountFactors issuerDiscountFactors =
        discountingProvider.issuerCurveDiscountFactors(bond.getLegalEntityId(), bond.getCurrency());
    PointSensitivityBuilder pointNominal = periodPricer.presentValueSensitivityWithZSpread(
        bond.getNominalPayment(), ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear);
    PointSensitivityBuilder pointCoupon = PointSensitivityBuilder.none();
    for (CapitalIndexedBondPaymentPeriod period : bond.getPeriodicPayments()) {
      if ((bond.hasExCouponPeriod() && period.getDetachmentDate().isAfter(referenceDate)) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(referenceDate))) {
        pointCoupon = pointCoupon.combinedWith(periodPricer.presentValueSensitivityWithZSpread(
            period, ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear));
      }
    }
    return pointNominal.combinedWith(pointCoupon);
  }