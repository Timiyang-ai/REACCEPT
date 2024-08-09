  @Test
  public void test_toTrade() {
    LocalDate tradeDate = LocalDate.of(2015, 12, 21); // 19, 20 weekend
    LocalDate startDate = LocalDate.of(2015, 12, 20);
    LocalDate endDate = LocalDate.of(2020, 12, 20);
    LocalDate settlementDate = LocalDate.of(2015, 12, 24);
    TradeInfo info = TradeInfo.builder().tradeDate(tradeDate).settlementDate(settlementDate).build();
    Tenor tenor = Tenor.TENOR_5Y;
    ImmutableCdsConvention base = ImmutableCdsConvention.of(NAME, GBP, ACT_360, P3M, BUSI_ADJ_STD, SETTLE_DAY_ADJ_STD);
    Cds product = Cds.builder()
        .legalEntityId(LEGAL_ENTITY)
        .paymentSchedule(
            PeriodicSchedule.builder()
                .startDate(startDate)
                .endDate(endDate)
                .frequency(P3M)
                .businessDayAdjustment(BUSI_ADJ_STD)
                .startDateBusinessDayAdjustment(BUSI_ADJ_STD)
                .endDateBusinessDayAdjustment(BusinessDayAdjustment.NONE)
                .stubConvention(StubConvention.SMART_INITIAL)
                .rollConvention(RollConventions.DAY_20)
                .build())
        .buySell(BUY)
        .currency(GBP)
        .dayCount(ACT_360)
        .notional(NOTIONAL)
        .fixedRate(COUPON)
        .paymentOnDefault(PaymentOnDefault.ACCRUED_PREMIUM)
        .protectionStart(ProtectionStartOfDay.BEGINNING)
        .stepinDateOffset(STEPIN_DAY_ADJ)
        .settlementDateOffset(SETTLE_DAY_ADJ_STD)
        .build();
    CdsTrade expected = CdsTrade.builder()
        .info(info)
        .product(product)
        .build();
    CdsTrade test1 = base.createTrade(LEGAL_ENTITY, tradeDate, tenor, BUY, NOTIONAL, COUPON, REF_DATA);
    assertThat(test1).isEqualTo(expected);
    CdsTrade test2 = base.createTrade(LEGAL_ENTITY, tradeDate, startDate, tenor, BUY, NOTIONAL, COUPON, REF_DATA);
    assertThat(test2).isEqualTo(expected);
    CdsTrade test3 = base.createTrade(LEGAL_ENTITY, tradeDate, startDate, endDate, BUY, NOTIONAL, COUPON, REF_DATA);
    assertThat(test3).isEqualTo(expected);
    CdsTrade test4 = base.toTrade(LEGAL_ENTITY, info, startDate, endDate, BUY, NOTIONAL, COUPON);
    assertThat(test4).isEqualTo(expected);

    AdjustablePayment upfront = AdjustablePayment.of(CurrencyAmount.of(GBP, 0.1 * NOTIONAL), settlementDate);
    CdsTrade expectedWithUf = CdsTrade.builder()
        .info(info)
        .product(product)
        .upfrontFee(upfront)
        .build();
    CdsTrade test5 = base.createTrade(LEGAL_ENTITY, tradeDate, tenor, BUY, NOTIONAL, COUPON, upfront, REF_DATA);
    assertThat(test5).isEqualTo(expectedWithUf);
    CdsTrade test6 = base.createTrade(LEGAL_ENTITY, tradeDate, startDate, tenor, BUY, NOTIONAL, COUPON, upfront, REF_DATA);
    assertThat(test6).isEqualTo(expectedWithUf);
    CdsTrade test7 = base.createTrade(LEGAL_ENTITY, tradeDate, startDate, endDate, BUY, NOTIONAL, COUPON, upfront, REF_DATA);
    assertThat(test7).isEqualTo(expectedWithUf);
    CdsTrade test8 = base.toTrade(LEGAL_ENTITY, info, startDate, endDate, BUY, NOTIONAL, COUPON, upfront);
    assertThat(test8).isEqualTo(expectedWithUf);
  }