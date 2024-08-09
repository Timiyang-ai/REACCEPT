protected Optional<SecretSeries> getSecretSeriesFor(Client client, String name) {
    Record r = dslContext
        .select(SECRETS.ID, SECRETS.NAME, SECRETS.DESCRIPTION, SECRETS.CREATEDAT, SECRETS.CREATEDBY,
            SECRETS.UPDATEDAT, SECRETS.UPDATEDBY, SECRETS.TYPE, SECRETS.OPTIONS)
        .from(SECRETS)
        .join(SECRETS_CONTENT).on(SECRETS.ID.eq(SECRETS_CONTENT.SECRETID))
        .join(ACCESSGRANTS).on(SECRETS.ID.eq(ACCESSGRANTS.SECRETID))
        .join(MEMBERSHIPS).on(ACCESSGRANTS.GROUPID.eq(MEMBERSHIPS.GROUPID))
        .join(CLIENTS).on(CLIENTS.ID.eq(MEMBERSHIPS.CLIENTID))
        .where(SECRETS.NAME.eq(name).and(CLIENTS.NAME.eq(client.getName())))
        .fetchOne();

    return Optional.ofNullable(r).map((rec) -> rec.map(new SecretSeriesMapper()));
  }