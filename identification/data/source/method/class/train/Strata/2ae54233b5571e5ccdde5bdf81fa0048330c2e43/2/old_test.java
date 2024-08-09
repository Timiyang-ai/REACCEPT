  @Test
  public void test_resolve_pay() {
    BulletPayment test = BulletPayment.builder()
        .payReceive(PayReceive.PAY)
        .value(GBP_P1000)
        .date(AdjustableDate.of(DATE_2015_06_30))
        .build();
    ResolvedBulletPayment expected = ResolvedBulletPayment.of(Payment.of(GBP_M1000, DATE_2015_06_30));
    assertThat(test.resolve(REF_DATA)).isEqualTo(expected);
  }