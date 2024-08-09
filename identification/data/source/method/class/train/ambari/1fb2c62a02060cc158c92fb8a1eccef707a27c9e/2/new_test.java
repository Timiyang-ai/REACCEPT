@Test
  public void testCreateUser_NoDuplicates() throws Exception {
    initForCreateUser(null);
    Users users = injector.getInstance(Users.class);
    users.createUser(SERVICEOP_USER_NAME, SERVICEOP_USER_NAME, SERVICEOP_USER_NAME);
  }