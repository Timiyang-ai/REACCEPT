  @Test public void getSecrets() {
    assertThat(secretDAO.getSecrets(null, null, null, null, null)).containsOnly(secret1, secret2b);
  }