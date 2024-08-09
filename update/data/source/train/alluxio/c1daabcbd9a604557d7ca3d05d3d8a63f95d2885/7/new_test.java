@Test
  public void defaults() throws IOException {
    MkdirsOptions options = MkdirsOptions.defaults(mConfiguration);

    // Verify the default createParent is true.
    assertTrue(options.getCreateParent());
    // Verify that the owner and group are not set.
    assertNull(options.getOwner());
    assertNull(options.getGroup());
    String umask = mConfiguration.get(PropertyKey.SECURITY_AUTHORIZATION_PERMISSION_UMASK);
    assertEquals(ModeUtils.applyDirectoryUMask(Mode.defaults(), umask), options.getMode());
  }