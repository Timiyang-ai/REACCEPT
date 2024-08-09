PointSensitivityBuilder presentValueSensitivity(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors issuerDiscountFactors =
        discountingProvider.issuerCurveDiscountFactors(bond.getLegalEntityId(), bond.getCurrency());
    PointSensitivityBuilder pointNominal =
        periodPricer.presentValueSensitivity(bond.getNominalPayment(), ratesProvider, issuerDiscountFactors);
    PointSensitivityBuilder pointCoupon = PointSensitivityBuilder.none();
    for (CapitalIndexedBondPaymentPeriod period : bond.getPeriodicPayments()) {
      if ((bond.hasExCouponPeriod() && period.getDetachmentDate().isAfter(referenceDate)) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(referenceDate))) {
        pointCoupon = pointCoupon.combinedWith(
            periodPricer.presentValueSensitivity(period, ratesProvider, issuerDiscountFactors));
      }
    }
    return pointNominal.combinedWith(pointCoupon);
  }