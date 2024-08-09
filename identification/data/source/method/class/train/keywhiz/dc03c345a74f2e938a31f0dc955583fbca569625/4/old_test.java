  @Test public void getSecretSeriesFor() throws Exception {
    SecretSeries secretSeries1 = secretSeriesDAO.getSecretSeriesById(secret1.getId()).get();

    aclDAO.enrollClient(jooqContext.configuration(), client2.getId(), group1.getId());
    aclDAO.enrollClient(jooqContext.configuration(), client2.getId(), group3.getId());
    aclDAO.enrollClient(jooqContext.configuration(), client2.getId(), group2.getId());
    aclDAO.allowAccess(jooqContext.configuration(), secret1.getId(), group1.getId());
    aclDAO.allowAccess(jooqContext.configuration(), secret1.getId(), group2.getId());

    SecretSeries secretSeries = aclDAO.getSecretSeriesFor(jooqContext.configuration(), client2, secret1.getName())
        .orElseThrow(RuntimeException::new);
    assertThat(secretSeries).isEqualToIgnoringGivenFields(secretSeries1, "id");

    aclDAO.evictClient(jooqContext.configuration(), client2.getId(), group1.getId());
    aclDAO.evictClient(jooqContext.configuration(), client2.getId(), group2.getId());
    assertThat(aclDAO.getSecretSeriesFor(jooqContext.configuration(), client2, secret1.getName()))
        .isEmpty();

    aclDAO.allowAccess(jooqContext.configuration(), secret1.getId(), group3.getId());

    secretSeries = aclDAO.getSecretSeriesFor(jooqContext.configuration(), client2, secret1.getName())
        .orElseThrow(RuntimeException::new);
    assertThat(secretSeries).isEqualToIgnoringGivenFields(secretSeries1, "id");
  }