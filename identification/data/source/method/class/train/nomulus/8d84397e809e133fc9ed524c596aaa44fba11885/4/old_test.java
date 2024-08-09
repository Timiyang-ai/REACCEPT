  @Test
  public void test_savePremiumListAndEntries_clearsCache() {
    assertThat(PremiumList.cachePremiumLists.getIfPresent("tld")).isNull();
    PremiumList pl = PremiumList.getCached("tld").get();
    assertThat(PremiumList.cachePremiumLists.getIfPresent("tld")).isEqualTo(pl);
    savePremiumListAndEntries(
        new PremiumList.Builder().setName("tld").build(), ImmutableList.of("test,USD 1"));
    assertThat(PremiumList.cachePremiumLists.getIfPresent("tld")).isNull();
  }