  @Test
  public void test_toTrade() {
    IborFixingDepositConvention convention = ImmutableIborFixingDepositConvention.builder()
        .businessDayAdjustment(BDA_MOD_FOLLOW)
        .currency(EUR)
        .dayCount(ACT_365F)
        .fixingDateOffset(FIXING_ADJ)
        .index(EUR_LIBOR_3M)
        .spotDateOffset(SPOT_ADJ)
        .build();
    LocalDate tradeDate = LocalDate.of(2015, 1, 22);
    Period depositPeriod = Period.ofMonths(3);
    double notional = 1d;
    double fixedRate = 0.045;
    IborFixingDepositTrade trade = convention.createTrade(tradeDate, depositPeriod, BUY, notional, fixedRate, REF_DATA);
    LocalDate startExpected = SPOT_ADJ.adjust(tradeDate, REF_DATA);
    LocalDate endExpected = startExpected.plus(depositPeriod);
    IborFixingDeposit productExpected = IborFixingDeposit.builder()
        .businessDayAdjustment(BDA_MOD_FOLLOW)
        .buySell(BUY)
        .currency(EUR)
        .dayCount(ACT_365F)
        .startDate(startExpected)
        .endDate(endExpected)
        .fixedRate(fixedRate)
        .fixingDateOffset(FIXING_ADJ)
        .index(EUR_LIBOR_3M)
        .notional(notional)
        .build();
    TradeInfo tradeInfoExpected = TradeInfo.builder()
        .tradeDate(tradeDate)
        .build();
    assertThat(trade.getProduct()).isEqualTo(productExpected);
    assertThat(trade.getInfo()).isEqualTo(tradeInfoExpected);
  }