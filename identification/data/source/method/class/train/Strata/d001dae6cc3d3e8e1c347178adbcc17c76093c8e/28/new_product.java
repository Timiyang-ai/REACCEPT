PointSensitivityBuilder presentValueSensitivity(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate) {

    ResolvedCapitalIndexedBond expanded = product.resolve(REF_DATA);
    IssuerCurveDiscountFactors issuerDiscountFactors = issuerDiscountFactorsProvider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    PointSensitivityBuilder pointNominal =
        periodPricer.presentValueSensitivity(expanded.getNominalPayment(), ratesProvider, issuerDiscountFactors);
    PointSensitivityBuilder pointCoupon = PointSensitivityBuilder.none();
    for (CapitalIndexedBondPaymentPeriod period : expanded.getPeriodicPayments()) {
      if ((product.getExCouponPeriod().getDays() != 0 && period.getDetachmentDate().isAfter(referenceDate)) ||
          (product.getExCouponPeriod().getDays() == 0 && period.getPaymentDate().isAfter(referenceDate))) {
        pointCoupon = pointCoupon.combinedWith(
            periodPricer.presentValueSensitivity(period, ratesProvider, issuerDiscountFactors));
      }
    }
    return pointNominal.combinedWith(pointCoupon);
  }