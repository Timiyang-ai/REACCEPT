@Test
  public void setSelectedDatabaseConnection() {
    AbstractSqoopJobEntryController controller = new TestSqoopJobEntryController();

    String database = "testing";
    controller.setSelectedDatabaseConnection(database);

    assertNull("Setting a database to one that doesn't exist in the JobMeta should not work", controller.getConfig().getDatabase());

    controller.getJobMeta().addDatabase(new DatabaseMeta(database, "MYSQL", null, null, null, null, null, null));
    controller.setSelectedDatabaseConnection(database);

    assertEquals(database, controller.getConfig().getDatabase());
  }