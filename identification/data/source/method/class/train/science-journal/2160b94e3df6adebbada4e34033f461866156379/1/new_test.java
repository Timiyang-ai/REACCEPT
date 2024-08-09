  @Test
  public void makeAccountKey() throws Exception {
    // Check that the three account keys are all different.
    assertThat(ACCOUNT_KEY_1).isNotEqualTo(ACCOUNT_KEY_2);
    assertThat(ACCOUNT_KEY_1).isNotEqualTo(ACCOUNT_KEY_3);
    assertThat(ACCOUNT_KEY_2).isNotEqualTo(ACCOUNT_KEY_3);

    // Check that account keys begin with NAMESPACE.
    assertThat(ACCOUNT_KEY_1).startsWith(NAMESPACE);
    assertThat(ACCOUNT_KEY_2).startsWith(NAMESPACE);
    assertThat(ACCOUNT_KEY_3).startsWith(NAMESPACE);

    // Check that namespace can't be null, empty, or contain a "_" or ":"
    assertThrows(
        IllegalArgumentException.class, () -> AccountsUtils.makeAccountKey(null, ACCOUNT_ID_1));
    assertThrows(
        IllegalArgumentException.class, () -> AccountsUtils.makeAccountKey("", ACCOUNT_ID_1));
    assertThrows(
        IllegalArgumentException.class,
        () -> AccountsUtils.makeAccountKey("com_google_test", ACCOUNT_ID_1));
    assertThrows(
        IllegalArgumentException.class,
        () -> AccountsUtils.makeAccountKey("com:google:test", ACCOUNT_ID_1));

    // Check that accountId can't be null or empty.
    assertThrows(
        IllegalArgumentException.class, () -> AccountsUtils.makeAccountKey(NAMESPACE, null));
    assertThrows(IllegalArgumentException.class, () -> AccountsUtils.makeAccountKey(NAMESPACE, ""));
  }