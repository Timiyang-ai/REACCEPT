@Test public void drop() {
    // create and drop local permission
    query(_USER_DROP.args(NAME, NAME));
    query(_USER_LIST_DETAILS.args() + "/database/@pattern = '" + NAME + '\'', "false");

    // drop list of permissions
    query(_USER_DROP.args(NAME, "('x','y')"));

    // invalid database pattern
    error(_USER_DROP.args(NAME, ";"), USER_PATTERN_X);
    // redundant operations
    error(_USER_DROP.args(NAME) + ',' + _USER_DROP.args(NAME), USER_UPDATE_X_X);
    error(_USER_DROP.args(NAME, 'x') + ',' + _USER_DROP.args(NAME, 'x'), USER_SAMEPAT_X);
    error(_USER_DROP.args(NAME) + ',' + _USER_ALTER.args(NAME, "X"), USER_CONFLICT_X);

    // drop user
    query(_USER_DROP.args(NAME));
    query(_USER_EXISTS.args(NAME), "false");

    // admin cannot be modified
    error(_USER_DROP.args(UserText.ADMIN), USER_ADMIN);
    // invalid name
    error(_USER_DROP.args(NAME, ""), USER_UNKNOWN_X);
    error(_USER_DROP.args(""), USER_NAME_X);
    error(_USER_DROP.args("", NAME), USER_NAME_X);
  }