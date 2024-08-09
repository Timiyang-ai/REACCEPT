  @Test
  public void getSanitizedSecretsWithGroupsAndCursor_allSecrets() {
    // Retrieving secrets with no parameters should retrieve all created secrets (although given
    // the shared database, it's likely to also retrieve others)
    List<SanitizedSecretWithGroups> retrievedSecrets = getAllSecretsWithCursor(null, null);
    assertListContainsSecretsWithIds(retrievedSecrets,
        ImmutableList.of(firstId, secondId, thirdId, fourthId, fifthId));

    retrievedSecrets = getAllSecretsWithCursor(null, 4);
    assertListContainsSecretsWithIds(retrievedSecrets,
        ImmutableList.of(firstId, secondId, thirdId, fourthId, fifthId));

    retrievedSecrets = getAllSecretsWithCursor(null, 3);
    assertListContainsSecretsWithIds(retrievedSecrets,
        ImmutableList.of(firstId, secondId, thirdId, fourthId, fifthId));

    retrievedSecrets = getAllSecretsWithCursor(null, 1);
    assertListContainsSecretsWithIds(retrievedSecrets,
        ImmutableList.of(firstId, secondId, thirdId, fourthId, fifthId));
  }