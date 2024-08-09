  @Test
  public void isValidService() throws AmbariException {
    boolean valid = metaInfo.isValidService(STACK_NAME_HDP, STACK_VERSION_HDP,
        SERVICE_NAME_HDFS);
    assertTrue(valid);

    boolean invalid = metaInfo.isValidService(STACK_NAME_HDP, STACK_VERSION_HDP, NON_EXT_VALUE);
    assertFalse(invalid);
  }