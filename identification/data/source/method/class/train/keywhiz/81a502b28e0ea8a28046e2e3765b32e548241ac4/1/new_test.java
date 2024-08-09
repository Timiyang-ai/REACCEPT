  @Test public void deleteSecretsByName() {
    secretDAO.createSecret("toBeDeleted_deleteSecretsByName", "encryptedShhh",
        cryptographer.computeHmac("encryptedShhh".getBytes(UTF_8), "hmackey"), "creator",
        ImmutableMap.of(), 0, "", null, null);

    secretDAO.deleteSecretsByName("toBeDeleted_deleteSecretsByName");

    Optional<SecretSeriesAndContent> secret =
        secretDAO.getSecretByName("toBeDeleted_deleteSecretsByName");
    assertThat(secret).isEmpty();
  }