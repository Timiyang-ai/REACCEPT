@Test public void exists() {
    query(_USER_EXISTS.args(UserText.ADMIN), true);
    query(_USER_EXISTS.args(NAME), true);
    query(_USER_EXISTS.args("unknown"), false);

    // invalid name
    error(_USER_EXISTS.args(""), USER_NAME_X);
  }