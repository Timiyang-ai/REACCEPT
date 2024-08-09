  @Test
  public void test_upfrontPayment() {
    Payment payment = TRADE_PRICER.upfrontPayment(TRADE);
    assertThat(payment.getCurrency()).isEqualTo(EUR);
    assertThat(payment.getAmount()).isCloseTo(-NOTIONAL * QUANTITY * DIRTY_PRICE, offset(TOL));
    assertThat(payment.getDate()).isEqualTo(SETTLEMENT);
  }