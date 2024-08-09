  @Test
  public void defaults() throws IOException {
    CreateOptions options = CreateOptions.defaults(mConfiguration);

    assertFalse(options.getCreateParent());
    assertFalse(options.isEnsureAtomic());
    assertNull(options.getOwner());
    assertNull(options.getGroup());
    String umask = mConfiguration.get(PropertyKey.SECURITY_AUTHORIZATION_PERMISSION_UMASK);
    assertEquals(ModeUtils.applyFileUMask(Mode.defaults(), umask), options.getMode());
  }