  @Test
  public void test_compareTo() {
    Currency a = Currency.EUR;
    Currency b = Currency.GBP;
    Currency c = Currency.JPY;
    assertThat(0).isEqualTo(a.compareTo(a));
    assertThat(0).isEqualTo(b.compareTo(b));
    assertThat(0).isEqualTo(c.compareTo(c));

    assertThat(a.compareTo(b) < 0).isTrue();
    assertThat(b.compareTo(a) > 0).isTrue();

    assertThat(a.compareTo(c) < 0).isTrue();
    assertThat(c.compareTo(a) > 0).isTrue();

    assertThat(b.compareTo(c) < 0).isTrue();
    assertThat(c.compareTo(b) > 0).isTrue();
  }