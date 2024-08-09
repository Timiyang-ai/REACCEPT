CurrencyAmount presentValue(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors issuerDiscountFactors = discountingProvider.issuerCurveDiscountFactors(
        bond.getLegalEntityId(), bond.getCurrency());
    double pvNominal =
        periodPricer.presentValue(bond.getNominalPayment(), ratesProvider, issuerDiscountFactors);
    double pvCoupon = 0d;
    for (CapitalIndexedBondPaymentPeriod period : bond.getPeriodicPayments()) {
      if ((bond.hasExCouponPeriod() && period.getDetachmentDate().isAfter(referenceDate)) ||
          (!bond.hasExCouponPeriod() && period.getPaymentDate().isAfter(referenceDate))) {
        pvCoupon += periodPricer.presentValue(period, ratesProvider, issuerDiscountFactors);
      }
    }
    return CurrencyAmount.of(bond.getCurrency(), pvCoupon + pvNominal);
  }