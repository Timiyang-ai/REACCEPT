@Test
  public void testGetRegistrarForUser_noUser() {
    expectGetRegistrarFailure(DEFAULT_CLIENT_ID, NO_USER, "Not logged in");
  }