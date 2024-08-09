  @Test
  public void test_resolve() {
    // test case
    KnownAmountSwapLeg test = KnownAmountSwapLeg.builder()
        .payReceive(PAY)
        .accrualSchedule(PeriodicSchedule.builder()
            .startDate(DATE_01_05)
            .endDate(DATE_04_05)
            .frequency(P1M)
            .businessDayAdjustment(BusinessDayAdjustment.of(FOLLOWING, GBLO))
            .build())
        .paymentSchedule(PaymentSchedule.builder()
            .paymentFrequency(P1M)
            .paymentDateOffset(PLUS_TWO_DAYS)
            .build())
        .amount(ValueSchedule.builder()
            .initialValue(123d)
            .steps(ValueStep.of(1, ValueAdjustment.ofReplace(234d)))
            .build())
        .currency(GBP)
        .build();
    // expected
    KnownAmountSwapPaymentPeriod rpp1 = KnownAmountSwapPaymentPeriod.builder()
        .payment(Payment.ofPay(CurrencyAmount.of(GBP, 123d), DATE_02_07))
        .startDate(DATE_01_06)
        .endDate(DATE_02_05)
        .unadjustedStartDate(DATE_01_05)
        .build();
    KnownAmountSwapPaymentPeriod rpp2 = KnownAmountSwapPaymentPeriod.builder()
        .payment(Payment.ofPay(CurrencyAmount.of(GBP, 234d), DATE_03_07))
        .startDate(DATE_02_05)
        .endDate(DATE_03_05)
        .build();
    KnownAmountSwapPaymentPeriod rpp3 = KnownAmountSwapPaymentPeriod.builder()
        .payment(Payment.ofPay(CurrencyAmount.of(GBP, 234d), DATE_04_09))
        .startDate(DATE_03_05)
        .endDate(DATE_04_07)
        .unadjustedEndDate(DATE_04_05)
        .build();
    // assertion
    assertThat(test.resolve(REF_DATA)).isEqualTo(ResolvedSwapLeg.builder()
        .type(FIXED)
        .payReceive(PAY)
        .paymentPeriods(rpp1, rpp2, rpp3)
        .build());
  }