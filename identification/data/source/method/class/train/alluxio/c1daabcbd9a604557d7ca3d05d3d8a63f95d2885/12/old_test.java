@Test
  public void defaults() throws IOException {
    MkdirsOptions options = MkdirsOptions.defaults();

    // Verify the default createParent is true.
    assertTrue(options.getCreateParent());
    // Verify that the owner and group are not set.
    assertNull(options.getOwner());
    assertNull(options.getGroup());
    assertEquals(ModeUtils.applyDirectoryUMask(Mode.defaults()), options.getMode());
  }