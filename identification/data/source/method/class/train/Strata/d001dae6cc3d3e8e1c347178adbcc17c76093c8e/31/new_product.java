PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bond, discountingProvider);
    PointSensitivityBuilder pointNominal = periodPricer.presentValueSensitivityWithZSpread(
        bond.getNominalPayment(), ratesProvider, issuerDf, zSpread, compoundedRateType, periodsPerYear);
    PointSensitivityBuilder pointCoupon = PointSensitivityBuilder.none();
    for (CapitalIndexedBondPaymentPeriod period : bond.getPeriodicPayments()) {
      if ((bond.hasExCouponPeriod() && period.getDetachmentDate().isAfter(referenceDate)) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(referenceDate))) {
        pointCoupon = pointCoupon.combinedWith(periodPricer.presentValueSensitivityWithZSpread(
            period, ratesProvider, issuerDf, zSpread, compoundedRateType, periodsPerYear));
      }
    }
    return pointNominal.combinedWith(pointCoupon);
  }