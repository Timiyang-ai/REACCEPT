  @Test
  public void test_toTrade() {
    TermDepositConvention convention = ImmutableTermDepositConvention.builder()
        .name("EUR-Dep")
        .businessDayAdjustment(BDA_MOD_FOLLOW)
        .currency(EUR)
        .dayCount(ACT_360)
        .spotDateOffset(PLUS_TWO_DAYS)
        .build();
    LocalDate tradeDate = LocalDate.of(2015, 1, 22);
    Period period3M = Period.ofMonths(3);
    BuySell buy = BuySell.BUY;
    double notional = 2_000_000d;
    double rate = 0.0125;
    TermDepositTrade trade = convention.createTrade(tradeDate, period3M, buy, notional, rate, REF_DATA);
    LocalDate startDateExpected = PLUS_TWO_DAYS.adjust(tradeDate, REF_DATA);
    LocalDate endDateExpected = startDateExpected.plus(period3M);
    TermDeposit termDepositExpected = TermDeposit.builder()
        .buySell(buy)
        .currency(EUR)
        .notional(notional)
        .startDate(startDateExpected)
        .endDate(endDateExpected)
        .businessDayAdjustment(BDA_MOD_FOLLOW)
        .rate(rate)
        .dayCount(ACT_360)
        .build();
    TradeInfo tradeInfoExpected = TradeInfo.of(tradeDate);
    assertThat(trade.getProduct()).isEqualTo(termDepositExpected);
    assertThat(trade.getInfo()).isEqualTo(tradeInfoExpected);
  }