  @Test
  public void test_createTrade() {
    IborFixingDepositTemplate template = IborFixingDepositTemplate.of(EUR_LIBOR_3M);
    double notional = 1d;
    double fixedRate = 0.045;
    LocalDate tradeDate = LocalDate.of(2015, 1, 22);
    IborFixingDepositTrade trade = template.createTrade(tradeDate, BUY, notional, fixedRate, REF_DATA);
    ImmutableIborFixingDepositConvention conv = (ImmutableIborFixingDepositConvention) template.getConvention();
    LocalDate startExpected = conv.getSpotDateOffset().adjust(tradeDate, REF_DATA);
    LocalDate endExpected = startExpected.plus(template.getDepositPeriod());
    IborFixingDeposit productExpected = IborFixingDeposit.builder()
        .businessDayAdjustment(conv.getBusinessDayAdjustment())
        .buySell(BUY)
        .startDate(startExpected)
        .endDate(endExpected)
        .fixedRate(fixedRate)
        .index(EUR_LIBOR_3M)
        .notional(notional)
        .build();
    TradeInfo tradeInfoExpected = TradeInfo.builder()
        .tradeDate(tradeDate)
        .build();
    assertThat(trade.getInfo()).isEqualTo(tradeInfoExpected);
    assertThat(trade.getProduct()).isEqualTo(productExpected);
  }