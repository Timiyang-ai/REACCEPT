CurrencyAmount presentValue(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate) {

    ResolvedCapitalIndexedBond expanded = product.resolve(REF_DATA);
    IssuerCurveDiscountFactors issuerDiscountFactors = issuerDiscountFactorsProvider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    double pvNominal =
        periodPricer.presentValue(expanded.getNominalPayment(), ratesProvider, issuerDiscountFactors);
    double pvCoupon = 0d;
    for (CapitalIndexedBondPaymentPeriod period : expanded.getPeriodicPayments()) {
      if ((product.getExCouponPeriod().getDays() != 0 && period.getDetachmentDate().isAfter(referenceDate)) ||
          (product.getExCouponPeriod().getDays() == 0 && period.getPaymentDate().isAfter(referenceDate))) {
        pvCoupon += periodPricer.presentValue(period, ratesProvider, issuerDiscountFactors);
      }
    }
    return CurrencyAmount.of(product.getCurrency(), pvCoupon + pvNominal);
  }