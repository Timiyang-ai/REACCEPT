@Test public void create() {
    // allow empty passwords, overwriting existing users
    query(_USER_CREATE.args(NAME, ""));
    // specify permission
    query(_USER_CREATE.args(NAME, NAME, Perm.ADMIN));

    // invalid permission
    error(_USER_CREATE.args(NAME, NAME, ""), USER_PERMISSION_X);
    // admin cannot be modified
    error(_USER_CREATE.args(UserText.ADMIN, ""), USER_ADMIN);
    // invalid name
    error(_USER_CREATE.args("", ""), USER_NAME_X);
    error(_USER_CREATE.args("", "", Perm.ADMIN), USER_NAME_X);

    // redundant operations
    error(_USER_CREATE.args(NAME, "") + ',' + _USER_CREATE.args(NAME, ""), USER_UPDATE_X_X);
  }