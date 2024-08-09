CurrencyAmount presentValueWithZSpread(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    ResolvedCapitalIndexedBond expanded = product.resolve(REF_DATA);
    IssuerCurveDiscountFactors issuerDiscountFactors = issuerDiscountFactorsProvider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    double pvNominal = periodPricer.presentValueWithZSpread(
            expanded.getNominalPayment(), ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear);
    double pvCoupon = 0d;
    for (CapitalIndexedBondPaymentPeriod period : expanded.getPeriodicPayments()) {
      if ((product.getExCouponPeriod().getDays() != 0 && period.getDetachmentDate().isAfter(referenceDate)) ||
          (product.getExCouponPeriod().getDays() == 0 && period.getPaymentDate().isAfter(referenceDate))) {
        pvCoupon += periodPricer.presentValueWithZSpread(
            period, ratesProvider, issuerDiscountFactors, zSpread, compoundedRateType, periodsPerYear);
      }
    }
    return CurrencyAmount.of(product.getCurrency(), pvCoupon + pvNominal);
  }