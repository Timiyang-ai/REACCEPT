@Test
  public void testGetDialogs() {
    final List<Dialog> dialogs = service.getDialogs();
    assertNotNull(dialogs);
    assertFalse(dialogs.isEmpty());
  }