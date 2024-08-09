  @Test
  public void test_createTrade() {
    TermDepositTemplate template = TermDepositTemplate.of(DEPOSIT_PERIOD, CONVENTION);
    LocalDate tradeDate = LocalDate.of(2015, 1, 23);
    BuySell buy = BuySell.BUY;
    double notional = 2_000_000d;
    double rate = 0.0125;
    TermDepositTrade trade = template.createTrade(tradeDate, buy, notional, rate, REF_DATA);
    TradeInfo tradeInfoExpected = TradeInfo.of(tradeDate);
    LocalDate startDateExpected = PLUS_TWO_DAYS.adjust(tradeDate, REF_DATA);
    LocalDate endDateExpected = startDateExpected.plus(DEPOSIT_PERIOD);
    TermDeposit productExpected = TermDeposit.builder()
        .buySell(buy)
        .currency(EUR)
        .notional(notional)
        .businessDayAdjustment(BDA_MOD_FOLLOW)
        .startDate(startDateExpected)
        .endDate(endDateExpected)
        .rate(rate)
        .dayCount(ACT_360)
        .build();
    assertThat(trade.getInfo()).isEqualTo(tradeInfoExpected);
    assertThat(trade.getProduct()).isEqualTo(productExpected);
  }