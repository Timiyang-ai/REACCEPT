@Test
  public void testAddWord() {
    model = createVoiceModel();
    final CustomTranslation expected = instantiateCustomTranslations().get(0);

    service.addWord(model, expected).execute();

    final List<CustomTranslation> results = service.getWords(model).execute();
    assertEquals(1, results.size());

    final CustomTranslation result = results.get(0);
    assertEquals(expected, result);
    assertEquals(expected.getWord(), result.getWord());
    assertEquals(expected.getTranslation(), result.getTranslation());
  }