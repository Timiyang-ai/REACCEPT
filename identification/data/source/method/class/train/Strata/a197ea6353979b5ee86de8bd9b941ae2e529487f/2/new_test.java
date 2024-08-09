  @Test
  public void test_resolve() {
    FixedCouponBond base = sut();
    ResolvedFixedCouponBond resolved = base.resolve(REF_DATA);
    assertThat(resolved.getLegalEntityId()).isEqualTo(LEGAL_ENTITY);
    assertThat(resolved.getSettlementDateOffset()).isEqualTo(DATE_OFFSET);
    assertThat(resolved.getYieldConvention()).isEqualTo(YIELD_CONVENTION);
    ImmutableList<FixedCouponBondPaymentPeriod> periodicPayments = resolved.getPeriodicPayments();
    int expNum = 20;
    assertThat(periodicPayments).hasSize(expNum);
    LocalDate unadjustedEnd = END_DATE;
    Schedule unadjusted = PERIOD_SCHEDULE.createSchedule(REF_DATA).toUnadjusted();
    for (int i = 0; i < expNum; ++i) {
      FixedCouponBondPaymentPeriod payment = periodicPayments.get(expNum - 1 - i);
      assertThat(payment.getCurrency()).isEqualTo(EUR);
      assertThat(payment.getNotional()).isEqualTo(NOTIONAL);
      assertThat(payment.getFixedRate()).isEqualTo(FIXED_RATE);
      assertThat(payment.getUnadjustedEndDate()).isEqualTo(unadjustedEnd);
      assertThat(payment.getEndDate()).isEqualTo(BUSINESS_ADJUST.adjust(unadjustedEnd, REF_DATA));
      assertThat(payment.getPaymentDate()).isEqualTo(payment.getEndDate());
      LocalDate unadjustedStart = unadjustedEnd.minusMonths(6);
      assertThat(payment.getUnadjustedStartDate()).isEqualTo(unadjustedStart);
      assertThat(payment.getStartDate()).isEqualTo(BUSINESS_ADJUST.adjust(unadjustedStart, REF_DATA));
      assertThat(payment.getYearFraction()).isEqualTo(unadjusted.getPeriod(expNum - 1 - i).yearFraction(DAY_COUNT, unadjusted));
      assertThat(payment.getDetachmentDate()).isEqualTo(EX_COUPON.adjust(payment.getPaymentDate(), REF_DATA));
      unadjustedEnd = unadjustedStart;
    }
    Payment expectedPayment = Payment.of(CurrencyAmount.of(EUR, NOTIONAL), BUSINESS_ADJUST.adjust(END_DATE, REF_DATA));
    assertThat(resolved.getNominalPayment()).isEqualTo(expectedPayment);
  }