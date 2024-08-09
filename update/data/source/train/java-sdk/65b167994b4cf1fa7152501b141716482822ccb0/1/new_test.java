@Test
  public void testGetWords() {
    List<WordData> result = service.getWords(customizationId, Type.ALL).execute();
    assertNotNull(result);
    assertTrue(!result.isEmpty());
  }