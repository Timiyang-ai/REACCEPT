@VisibleForTesting void pruneOldContents(long secretId) {
    // Fetch current version number
    SecretsRecord secret =
        dslContext.select(SECRETS.CURRENT)
            .from(SECRETS)
            .where(SECRETS.ID.eq(secretId))
            .fetchOneInto(SecretsRecord.class);

    if (secret == null || secret.getCurrent() == null) {
      // No current secret assigned (secret just being created), let's not prune right now.
      return;
    }

    // Select everything older than cutoff for possible pruning
    long cutoff = OffsetDateTime.now().minusDays(PRUNE_CUTOFF_DAYS).toEpochSecond();

    List<Long> records =
        dslContext.select(SECRETS_CONTENT.ID)
            .from(SECRETS_CONTENT)
            .where(SECRETS_CONTENT.SECRETID.eq(secretId))
            .and(SECRETS_CONTENT.CREATEDAT.lt(cutoff))
            .and(SECRETS_CONTENT.ID.ne(secret.getCurrent()))
            .orderBy(SECRETS_CONTENT.CREATEDAT.desc())
            .fetch(SECRETS_CONTENT.ID);

    // Always keep last X items, prune otherwise
    if (records.size() > PRUNE_CUTOFF_ITEMS) {
      for (long id : records.subList(PRUNE_CUTOFF_ITEMS, records.size())) {
        dslContext.deleteFrom(SECRETS_CONTENT).where(SECRETS_CONTENT.ID.eq(id)).execute();
      }
    }
  }