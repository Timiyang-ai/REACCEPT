  @Test
  public void create() {
    assertEquals(DB_NAME, HiveDatabase.create(mUdbContext, mUdbConf).getName());
  }