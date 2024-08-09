  @Test
  public void isSupportedStack() throws AmbariException {
    boolean supportedStack = metaInfo.isSupportedStack(STACK_NAME_HDP,
        STACK_VERSION_HDP);
    assertTrue(supportedStack);

    boolean notSupportedStack = metaInfo.isSupportedStack(NON_EXT_VALUE,
        NON_EXT_VALUE);
    assertFalse(notSupportedStack);
  }