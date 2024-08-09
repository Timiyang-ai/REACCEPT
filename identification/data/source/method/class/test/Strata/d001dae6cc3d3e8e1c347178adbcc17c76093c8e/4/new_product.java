PointSensitivityBuilder presentValueSensitivity(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bond, discountingProvider);
    PointSensitivityBuilder pointNominal =
        periodPricer.presentValueSensitivity(bond.getNominalPayment(), ratesProvider, issuerDf);
    PointSensitivityBuilder pointCoupon = PointSensitivityBuilder.none();
    for (CapitalIndexedBondPaymentPeriod period : bond.getPeriodicPayments()) {
      if ((bond.hasExCouponPeriod() && period.getDetachmentDate().isAfter(referenceDate)) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(referenceDate))) {
        pointCoupon = pointCoupon.combinedWith(
            periodPricer.presentValueSensitivity(period, ratesProvider, issuerDf));
      }
    }
    return pointNominal.combinedWith(pointCoupon);
  }