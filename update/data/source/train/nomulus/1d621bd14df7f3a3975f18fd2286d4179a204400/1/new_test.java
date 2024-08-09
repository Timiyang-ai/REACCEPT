@Test
  public void testGetRegistrarForUser_readOnly_noUser() {
    expectGetRegistrarFailure(DEFAULT_CLIENT_ID, READ_ONLY, NO_USER, "Not logged in");
  }