public SanitizedSecretWithGroupsListAndCursor getSanitizedSecretsWithGroupsAndCursor(
      @Nullable Long expireMaxTime,
      @Nullable Integer limit,
      @Nullable SecretRetrievalCursor cursor) {
    // Retrieve secrets based on the cursor (if provided).
    ImmutableList<SecretSeriesAndContent> secrets;

    // Retrieve one additional record to detect when information is missing
    Integer updatedLimit = null;
    if (limit != null) {
      updatedLimit = limit + 1;
    }

    if (cursor == null) {
      secrets = secretDAO.getSecrets(expireMaxTime, null, null, null, updatedLimit, 0);
    } else {
      secrets = secretDAO.getSecrets(expireMaxTime, null, cursor.expiry(), cursor.name(),
          updatedLimit, 0);
    }

    // Set the cursor and strip the final record from the secrets if necessary
    SecretRetrievalCursor newCursor = null;
    if (limit != null && secrets.size() > limit) {
      // The name and expiry in the new cursor will be the first entry in the next set of results
      newCursor = SecretRetrievalCursor.of(secrets.get(limit).series().name(),
          secrets.get(limit).content().expiry());
      // Trim the last record from the list
      secrets = secrets.subList(0, limit);
    }

    Set<Long> secretIds = secrets.stream().map(s -> s.series().id()).collect(toSet());

    Map<Long, List<Group>> groupsForSecrets = aclDAO.getGroupsForSecrets(secretIds);

    List<SanitizedSecretWithGroups> secretsWithGroups = secrets.stream().map(s -> {
      List<Group> groups = groupsForSecrets.get(s.series().id());
      if (groups == null) {
        groups = ImmutableList.of();
      }
      return fromSecretSeriesAndContentAndGroups(s, groups);
    }).collect(toList());

    try {
      return SanitizedSecretWithGroupsListAndCursor.of(secretsWithGroups,
          SecretRetrievalCursor.toUrlEncodedString(newCursor));
    } catch (Exception e) {
      logger.warn("Unable to encode cursor to string (cursor: {}): {}", newCursor, e.getMessage());
      // The cursor is malformed; return what information could be gathered
      return SanitizedSecretWithGroupsListAndCursor.of(secretsWithGroups, null);
    }
  }