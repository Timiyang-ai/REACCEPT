@Test
  public void testGetWords() {
    List<Word> result = service.getWords(customizationId, Type.ALL).execute();
    assertNotNull(result);
    assertTrue(!result.isEmpty());
  }