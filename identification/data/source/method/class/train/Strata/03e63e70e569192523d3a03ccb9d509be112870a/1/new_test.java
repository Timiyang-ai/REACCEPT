  @Test
  public void test_isConventional() {
    assertThat(CurrencyPair.of(GBP, USD).isConventional()).isEqualTo(true);
    assertThat(CurrencyPair.of(USD, GBP).isConventional()).isEqualTo(false);
    // There is no configuration for GBP/BRL or BRL/GBP so the ordering list is used to choose a convention pair
    // GBP is in the currency order list and BRL isn't so GBP is the base
    assertThat(CurrencyPair.of(GBP, BRL).isConventional()).isEqualTo(true);
    assertThat(CurrencyPair.of(BRL, GBP).isConventional()).isEqualTo(false);
    // There is no configuration for BHD/BRL or BRL/BHD and neither are in the list specifying currency priority order.
    // Lexicographical ordering is used
    assertThat(CurrencyPair.of(BHD, BRL).isConventional()).isEqualTo(true);
    assertThat(CurrencyPair.of(BRL, BHD).isConventional()).isEqualTo(false);
    assertThat(CurrencyPair.of(GBP, GBP).isConventional()).isEqualTo(true);
  }