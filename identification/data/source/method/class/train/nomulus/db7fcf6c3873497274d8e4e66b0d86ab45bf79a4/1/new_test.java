  @Test
  public void checkExists_worksSuccessfully() {
    assertThat(PremiumListDao.checkExists("testlist")).isFalse();
    PremiumListDao.saveNew(PremiumList.create("testlist", USD, TEST_PRICES));
    assertThat(PremiumListDao.checkExists("testlist")).isTrue();
  }