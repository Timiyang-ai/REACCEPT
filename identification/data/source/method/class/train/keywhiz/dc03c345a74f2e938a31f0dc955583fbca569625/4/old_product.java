@SingleValueResult(SecretSeries.class)
  @SqlQuery("SELECT secrets.id, secrets.name, secrets.description, secrets.metadata, " +
            "secrets.createdAt, secrets.createdBy, secrets.updatedAt, secrets.updatedBy, " +
            "secrets.type, secrets.options " +
            "FROM secrets JOIN secrets_content on secrets.id = secrets_content.secretId " +
            "JOIN accessGrants ON secrets.id = accessGrants.secretId " +
            "JOIN memberships ON accessGrants.groupId = memberships.groupId " +
            "JOIN clients ON clients.id = memberships.clientId " +
            "WHERE secrets.name = :name AND clients.name = :c.name")
  protected abstract Optional<SecretSeries> getSecretSeriesFor(@BindBean("c") Client client, @Bind("name") String name);