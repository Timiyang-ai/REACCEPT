  @Test
  public void test_parSpread() {
    SimpleRatesProvider prov = provider(VAL_DATE, DF_START, DF_END);
    double parSpread = PRICER.parSpread(RTERM_DEPOSIT, prov);
    TermDeposit depositPar = TermDeposit.builder()
        .buySell(BuySell.BUY)
        .startDate(START_DATE)
        .endDate(END_DATE)
        .businessDayAdjustment(BD_ADJ)
        .dayCount(ACT_360)
        .notional(NOTIONAL)
        .currency(EUR)
        .rate(RATE + parSpread)
        .build();
    double pvPar = PRICER.presentValue(depositPar.resolve(REF_DATA), prov).getAmount();
    assertThat(pvPar).isCloseTo(0.0, offset(NOTIONAL * TOLERANCE));
  }