@Test public void drop() {
    // create and drop local permission
    query(_USER_DROP.args(NAME, NAME));
    query(_USER_LIST_DETAILS.args() + "/database/@name = '" + NAME + '\'', "false");

    // drop user
    query(_USER_DROP.args(NAME));
    query(_USER_EXISTS.args(NAME), "false");

    // admin cannot be modified
    error(_USER_DROP.args(UserText.ADMIN), BXUS_ADMIN);
    // invalid name
    error(_USER_DROP.args(""), BXUS_NAME_X);
    error(_USER_DROP.args("", NAME), BXUS_NAME_X);
  }