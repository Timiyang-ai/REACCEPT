  @Test
  public void test_getDomainCreateCost_multipleYears() {
    assertThat(getDomainCreateCost("espresso.moka", clock.nowUtc(), 1))
        .isEqualTo(Money.parse("USD 13"));
    assertThat(getDomainCreateCost("espresso.moka", clock.nowUtc(), 5))
        .isEqualTo(Money.parse("USD 65"));
    assertThat(getDomainCreateCost("fraction.moka", clock.nowUtc(), 1))
        .isEqualTo(Money.parse("USD 20.50"));
    assertThat(getDomainCreateCost("fraction.moka", clock.nowUtc(), 3))
        .isEqualTo(Money.parse("USD 61.50"));
  }