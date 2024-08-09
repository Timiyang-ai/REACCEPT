  @Test
  public void isValid() throws Exception {
    assertTrue(PropertyKey.isValid(PropertyKey.HOME.toString()));
    assertTrue(PropertyKey
        .isValid(PropertyKey.Template.MASTER_MOUNT_TABLE_ALLUXIO.format("foo").toString()));
    assertFalse(PropertyKey.isValid(""));
    assertFalse(PropertyKey.isValid(" "));
    assertFalse(PropertyKey.isValid("foo"));
    assertFalse(PropertyKey.isValid(PropertyKey.HOME.toString() + "1"));
    assertFalse(PropertyKey.isValid(PropertyKey.HOME.toString().toUpperCase()));
  }