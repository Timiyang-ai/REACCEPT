@Test public void create() {
    // allow empty passwords, overwriting existing users
    query(_USER_CREATE.args(NAME, ""));
    // specify permissions
    query(_USER_CREATE.args(NAME, NAME, Perm.ADMIN));
    query(_USER_CREATE.args(NAME, NAME, "('admin','none')", "('','x')"));

    // invalid permission
    error(_USER_CREATE.args(NAME, NAME, ""), USER_PERMISSION_X);
    // admin cannot be modified
    error(_USER_CREATE.args(UserText.ADMIN, ""), USER_ADMIN);
    // invalid name
    error(_USER_CREATE.args("", ""), USER_NAME_X);
    error(_USER_CREATE.args("", "", Perm.ADMIN), USER_NAME_X);

    // redundant operations
    error(_USER_CREATE.args(NAME, "") + ',' + _USER_CREATE.args(NAME, ""), USER_UPDATE_X_X);
    error(_USER_CREATE.args(NAME, "", "('admin','admin')", "('','')"), USER_SAMEPERM_X_X);
    error(_USER_CREATE.args(NAME, "", "('admin','admin')", "('x','x')"), USER_SAMEPAT_X);
  }