@Test
  public void setSelectedDatabaseConnection() throws KettleDatabaseException {
    AbstractSqoopJobEntryController controller = new TestSqoopJobEntryController();

    String connect = "jdbc:bogus://bogus";
    String username = "username";
    String password = "password";
    controller.getConfig().setConnect(connect);
    controller.getConfig().setUsername(username);
    controller.getConfig().setPassword(password);

    String connectAdvanced = "jdbc:advanced://bogus";
    String usernameAdvanced = "advanced_user";
    String passwordAdvanced = "super password!";
    controller.getConfig().setConnectFromAdvanced(connectAdvanced);
    controller.getConfig().setUsernameFromAdvanced(usernameAdvanced);
    controller.getConfig().setPasswordFromAdvanced(passwordAdvanced);

    DatabaseItem test = new DatabaseItem("test");
    DatabaseMeta database = new DatabaseMeta(test.getName(), "MYSQL", null, null, null, null, null, null);
    controller.getJobMeta().addDatabase(database);
    controller.setSelectedDatabaseConnection(test);

    assertEquals(test, controller.getSelectedDatabaseConnection());
    assertEquals(test.getName(), controller.getConfig().getDatabase());
    assertEquals(database.getURL(), controller.getConfig().getConnect());
    assertEquals(database.getUsername(), controller.getConfig().getUsername());
    assertEquals(database.getPassword(), controller.getConfig().getPassword());

    assertEquals(connectAdvanced, controller.getConfig().getConnectFromAdvanced());
    assertEquals(usernameAdvanced, controller.getConfig().getUsernameFromAdvanced());
    assertEquals(passwordAdvanced, controller.getConfig().getPasswordFromAdvanced());

    controller.setSelectedDatabaseConnection(controller.USE_ADVANCED_OPTIONS);
    assertEquals(controller.USE_ADVANCED_OPTIONS, controller.getSelectedDatabaseConnection());
    assertNull(controller.getConfig().getDatabase());
    assertEquals(connectAdvanced, controller.getConfig().getConnect());
    assertEquals(usernameAdvanced, controller.getConfig().getUsername());
    assertEquals(passwordAdvanced, controller.getConfig().getPassword());
    assertEquals(connectAdvanced, controller.getConfig().getConnectFromAdvanced());
    assertEquals(usernameAdvanced, controller.getConfig().getUsernameFromAdvanced());
    assertEquals(passwordAdvanced, controller.getConfig().getPasswordFromAdvanced());

    controller.setSelectedDatabaseConnection(test);
    assertEquals(test, controller.getSelectedDatabaseConnection());
    assertEquals(test.getName(), controller.getConfig().getDatabase());
    assertEquals(database.getURL(), controller.getConfig().getConnect());
    assertEquals(database.getUsername(), controller.getConfig().getUsername());
    assertEquals(database.getPassword(), controller.getConfig().getPassword());

    assertEquals(connectAdvanced, controller.getConfig().getConnectFromAdvanced());
    assertEquals(usernameAdvanced, controller.getConfig().getUsernameFromAdvanced());
    assertEquals(passwordAdvanced, controller.getConfig().getPasswordFromAdvanced());
  }