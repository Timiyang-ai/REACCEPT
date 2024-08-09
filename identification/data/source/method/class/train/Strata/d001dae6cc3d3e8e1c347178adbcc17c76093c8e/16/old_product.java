PointSensitivityBuilder presentValueSensitivityWithZSpread(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    ExpandedCapitalIndexedBond expanded = product.expand();
    IssuerCurveDiscountFactors issuerDiscountFactors = issuerDiscountFactorsProvider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    PointSensitivityBuilder pointNominal = periodPricer.presentValueSensitivityWithZSpread(
        expanded.getNominalPayment(), ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear);
    PointSensitivityBuilder pointCoupon = PointSensitivityBuilder.none();
    for (CapitalIndexedBondPaymentPeriod period : expanded.getPeriodicPayments()) {
      if ((product.getExCouponPeriod().getDays() != 0 && period.getDetachmentDate().isAfter(referenceDate)) ||
          (product.getExCouponPeriod().getDays() == 0 && period.getPaymentDate().isAfter(referenceDate))) {
        pointCoupon = pointCoupon.combinedWith(periodPricer.presentValueSensitivityWithZSpread(
            period, ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear));
      }
    }
    return pointNominal.combinedWith(pointCoupon);
  }