@Override
  public ResolvedCds resolve(ReferenceData refData) {
    Period paymentInterval = getFeeLeg().getPeriodicPayments().getPaymentFrequency().getPeriod();
    StubConvention stubConvention = getFeeLeg().getPeriodicPayments().getStubConvention();
    DayCount accrualDayCount = getFeeLeg().getPeriodicPayments().getDayCount();
    double upfrontFeeAmount = getFeeLeg().getUpfrontFee().getAmount();
    LocalDate upfrontFeePaymentDate = getFeeLeg().getUpfrontFee().getDate();
    double coupon = getFeeLeg().getPeriodicPayments().getCoupon();
    double notional = getFeeLeg().getPeriodicPayments().getNotional().getAmount();
    Currency currency = getFeeLeg().getPeriodicPayments().getNotional().getCurrency();

    return ResolvedCds
        .builder()
        .buySellProtection(buySellProtection)
        .currency(currency)
        .notional(notional)
        .coupon(coupon)
        .startDate(startDate)
        .endDate(endDate)
        .businessDayAdjustment(businessDayAdjustment)
        .referenceInformation(referenceInformation)
        .payAccruedOnDefault(payAccruedOnDefault)
        .paymentInterval(paymentInterval)
        .stubConvention(stubConvention)
        .accrualDayCount(accrualDayCount)
        .accrualDayCount(accrualDayCount)
        .upfrontFeeAmount(upfrontFeeAmount)
        .upfrontFeePaymentDate(upfrontFeePaymentDate)
        .build();
  }