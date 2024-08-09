@Test
  public void users() throws BaseXException {
    // check if the admin user exists
    query(_ADMIN_USERS.args() + "= 'admin'", "true");
    // check if the temporarily created user is found
    new CreateUser(NAME, md5(NAME)).execute(context);
    query(_ADMIN_USERS.args() + "= '" + NAME + '\'', "true");
    // check if local user is found
    new Grant(Perm.READ, NAME, NAME).execute(context);
    query(_ADMIN_USERS.args(NAME) + "= '" + NAME + '\'', "true");
    // check if user has been removed
    new DropUser(NAME).execute(context);
    query(_ADMIN_USERS.args(NAME) + "= '" + NAME + '\'', "false");
    query(_ADMIN_USERS.args() + "= '" + NAME + '\'', "false");
  }