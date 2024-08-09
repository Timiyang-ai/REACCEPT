  @Test
  public void test_getDomainRenewCost_multipleYears() {
    assertThat(getDomainRenewCost("espresso.moka", clock.nowUtc(), 1))
        .isEqualTo(Money.parse("USD 11"));
    assertThat(getDomainRenewCost("espresso.moka", clock.nowUtc(), 5))
        .isEqualTo(Money.parse("USD 55"));
    assertThat(getDomainRenewCost("fraction.moka", clock.nowUtc(), 1))
        .isEqualTo(Money.parse("USD 20.50"));
    assertThat(getDomainRenewCost("fraction.moka", clock.nowUtc(), 3))
        .isEqualTo(Money.parse("USD 61.50"));
  }