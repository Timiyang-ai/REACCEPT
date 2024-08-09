  @Test
  public void saveNew_worksSuccessfully() {
    PremiumList premiumList = PremiumList.create("testname", USD, TEST_PRICES);
    PremiumListDao.saveNew(premiumList);
    jpaTm()
        .transact(
            () -> {
              PremiumList persistedList =
                  jpaTm()
                      .getEntityManager()
                      .createQuery(
                          "SELECT pl FROM PremiumList pl WHERE pl.name = :name", PremiumList.class)
                      .setParameter("name", "testname")
                      .getSingleResult();
              assertThat(persistedList.getLabelsToPrices()).containsExactlyEntriesIn(TEST_PRICES);
              assertThat(persistedList.getCreationTimestamp())
                  .isEqualTo(jpaRule.getTxnClock().nowUtc());
            });
  }