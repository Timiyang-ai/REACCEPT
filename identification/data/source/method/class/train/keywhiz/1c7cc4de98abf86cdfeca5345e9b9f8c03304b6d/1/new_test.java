  @Test public void pruneOldContents() throws Exception {
    long now = OffsetDateTime.now().toEpochSecond();
    long secretSeriesId = 666;

    jooqContext.insertInto(SECRETS, SECRETS.ID, SECRETS.NAME, SECRETS.CREATEDAT, SECRETS.UPDATEDAT)
        .values(secretSeriesId, "secretForPruneTest1", now, now)
        .execute();

    int before = tableSize();

    // Create contents
    long[] ids = new long[15];
    for (int i = 0; i < ids.length; i++) {
      long id = secretContentDAO.createSecretContent(secretSeriesId, "encrypted", "checksum",
          "creator", metadata, 1136214245, i);
      ids[i] = id;
    }

    assertThat(tableSize()).isEqualTo(before + ids.length);

    // Make most recent id be the current version for the secret series and prune
    jooqContext.update(SECRETS)
        .set(SECRETS.CURRENT, ids[ids.length-1])
        .where(SECRETS.ID.eq(secretSeriesId))
        .execute();

    secretContentDAO.pruneOldContents(secretSeriesId);

    // Last ten secrets in series should have survived (plus the current one)
    assertThat(tableSize()).isEqualTo(before + PRUNE_CUTOFF_ITEMS + 1);

    for (int i = 0; i < (ids.length - PRUNE_CUTOFF_ITEMS - 1); i++) {
      assertThat(secretContentDAO.getSecretContentById(ids[i])).isEmpty();
    }
    for (int i = (ids.length - PRUNE_CUTOFF_ITEMS - 1); i < ids.length; i++) {
      assertThat(secretContentDAO.getSecretContentById(ids[i])).isPresent();
    }

    // Other secrets contents left intact
    assertThat(secretContentDAO.getSecretContentById(secretContent1.id())).isPresent();
  }