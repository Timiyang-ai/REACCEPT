protected Optional<SecretSeries> getSecretSeriesFor(Client client, String name) {
    SecretsRecord r = dslContext
        .select()
        .from(SECRETS)
        .join(SECRETS_CONTENT).on(SECRETS.ID.eq(SECRETS_CONTENT.SECRETID))
        .join(ACCESSGRANTS).on(SECRETS.ID.eq(ACCESSGRANTS.SECRETID))
        .join(MEMBERSHIPS).on(ACCESSGRANTS.GROUPID.eq(MEMBERSHIPS.GROUPID))
        .join(CLIENTS).on(CLIENTS.ID.eq(MEMBERSHIPS.CLIENTID))
        .where(SECRETS.NAME.eq(name).and(CLIENTS.NAME.eq(client.getName())))
        .fetchOneInto(SECRETS);

    return Optional.ofNullable(r).map(
        rec -> new SecretSeriesMapper(mapper).map(rec));
  }