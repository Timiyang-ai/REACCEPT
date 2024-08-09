@Test
  public void testGetWord() {
    model = createVoiceModel();
    final List<CustomTranslation> expected = instantiateCustomTranslations();

    service.addWords(model, expected.toArray(new CustomTranslation[] { })).execute();

    final CustomTranslation word = service.getWord(model, expected.get(0).getWord()).execute();
    assertEquals(expected.get(0).getTranslation(), word.getTranslation());
  }